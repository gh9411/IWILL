package com.project.service.will;

import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.dao.will.FileDAO;
import com.project.model.will.WillEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.validation.Valid;

import com.project.service.will.FileService;
import com.project.util.HexToString;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


import org.springframework.stereotype.Controller;


import lombok.extern.slf4j.Slf4j;
@Service
public class FileService {
    @Autowired
    FileDAO filedao;

    public List<WillEntity> findAll(){
        return filedao.findAll();
    }

    public HashMap<String,String> upload(final MultipartFile multipartFile,String uid,String date) throws Exception {
		
		//폴더생성
		String path = "/home/ubuntu/image/"+uid+"/"+date; //폴더 경로
		File Folder = new File(path);

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try{
				Folder.mkdir(); //폴더 생성합니다.
			} 
			catch(Exception e){
				e.getStackTrace();
			}        
		}
		else {
		}
		
		
		//1. 파일 업로드

		final File targetFile = new File(path+"/"+ multipartFile.getOriginalFilename()); // 저장 위치 입력
		try {
			final InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
		} catch (final IOException e) {
			FileUtils.deleteQuietly(targetFile);
			e.printStackTrace();
		}
		
		HashMap<String,String> hm = new HashMap<>();
		hm.put("path", targetFile.getAbsolutePath());
		hm.put("hash", extractFileHashSHA256(targetFile.toString()));

		return hm;
        // 해쉬값 추출
        // String filehash = checkHash(targetFile.toString());
        
		// 블록체인에 해쉬값 저장
		// String targetUrl = "http://0.0.0.0:8545";

		//현재 getTransactionByHash로 테스트
		//보내는사람 account key 파일 hash 저장 필요
		// String jsonValue = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionByHash\",\"params\":[\"0x6d6491f8b81031dda775fc38ba08a4f3b5f8bb56e17951b5853f9a80e3ceb363\"],\"id\":2}";
		// String jsonValue = "{
		// 	"jsonrpc":"2.0",
		// 	"method":"personal_unlockAccount",
		// 	"params": [
		// 		"0x34ee5e2e9842d03c4000e9b2c70f398b04a69004",
		// 		"123qwe",
		// 		0
		// 		],   
		// 	"id":100
		// }";
        // String transhash = sendPost(targetUrl, jsonValue);

		// System.out.println(transhash);
        // DB 저장
    }



    public String checkHash(String path) throws Exception {

        System.out.println(extractStringHashSHA256(path));
		System.out.println(extractFileHashSHA256(path));

        return extractFileHashSHA256(path);
    }

    //https://bluexmas.tistory.com/383 참조
    public static String extractStringHashSHA256(String str){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
	
	public String extractFileHashSHA256(String filename) throws Exception {
		
		String SHA = ""; 
		int buff = 16384;
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");

			MessageDigest hashSum = MessageDigest.getInstance("SHA-256");

			byte[] buffer = new byte[buff];
			byte[] partialHash = null;

			long read = 0;

			// calculate the hash of the hole file for the test
			long offset = file.length();
			int unitsize;
			while (read < offset) {
				unitsize = (int) (((offset - read) >= buff) ? buff : (offset - read));
				file.read(buffer, 0, unitsize);

				hashSum.update(buffer, 0, unitsize);

				read += unitsize;
			}

			file.close();
			partialHash = new byte[hashSum.getDigestLength()];
			partialHash = hashSum.digest();
			System.out.println("partialHash : " + partialHash);
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < partialHash.length ; i++){
				sb.append(Integer.toString((partialHash[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		}
		return SHA;
	}

	// HTTP POST request
	public String sendPost(String targetUrl, String jsonValue) throws Exception {

		String inputLine = null;
		StringBuffer outResult = new StringBuffer();

		try {
			System.out.println("Rest api - Send Post start");

			URL url  = new URL(targetUrl);

			HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);

			OutputStream os = conn.getOutputStream();
			os.write(jsonValue.getBytes("UTF-8"));
			os.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			while((inputLine = in.readLine()) != null){
				outResult.append(inputLine);
			}

			conn.disconnect();

			System.out.println("Rest api - Send Post End");

		} catch (Exception e) {
			//TODO: handle exception
			System.out.println("Rest api - Send Post Error");
			e.printStackTrace();
		}
		// outResult == reponse data => json으로 변환해서 




		return outResult.toString();
	}

	public HashMap<String,String> uploadtxt(String content,String uid,String date) throws Exception{

		//폴더생성
		String path = "/home/ubuntu/image/"+uid+"/"+date; //폴더 경로
		File Folder = new File(path);

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try{
				Folder.mkdir(); //폴더 생성합니다.
			} 
			catch(Exception e){
				e.getStackTrace();
			}        
		}
		else {
		}
		
		
		//1. 파일 업로드

		File file = new File(path+"/content.txt");
		String str = content;
		HashMap<String,String> hm = new HashMap<>();
		
		try {
			System.out.println(str);
			FileOutputStream output = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(output,"UTF-8");
			BufferedWriter out = new BufferedWriter(writer);
			out.write(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		hm.put("path", file.toString());
		hm.put("hash", extractFileHashSHA256(file.toString()));
		return hm;

	}	
}
