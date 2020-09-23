package com.project.service.will;

import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.OutputStream;
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

    public HashMap<String,String> upload(final MultipartFile multipartFile) throws Exception {
        //1. 파일 업로드

		final File targetFile = new File("c:\\data\\" + multipartFile.getOriginalFilename()); // 저장 위치 입력
		System.out.println(targetFile.toPath());
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
	
	public static String extractFileHashSHA256(String filename) throws Exception {
		
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
			e.printStackTrace();
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

	public HashMap<String,String> uploadtxt(String content) throws Exception{

		File file = new File("c:\\data\\text.txt");
		String str = "Hello world!";
		HashMap<String,String> hm = new HashMap<>();
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		hm.put("path", file.toString());
		hm.put("hash", extractFileHashSHA256(file.toString()));
		return hm;

	}

	public static void main(String[] args) throws Exception{
		FileService fs = new FileService();

		//sendtransaction body data
		
		JSONObject ob = new JSONObject();
        ob.put("title", extractFileHashSHA256(new File("c:\\data\\text.txt").toString()));
        ob.put("picture", extractFileHashSHA256(new File("c:\\data\\image.jpg").toString()));
        ob.put("video", extractFileHashSHA256(new File("c:\\data\\video.avi").toString()));
        //senddata to hex
		String inputString = "0x"+ob.toString();
		System.out.println("==========json data==========");
		System.out.println(ob.toString());
		System.out.println();
        

        JSONObject sendobj = new JSONObject();
        sendobj.put("jsonrpc", "2.0");
        sendobj.put("method", "eth_sendTransaction");
        JSONArray params = new JSONArray();
        JSONObject paramsobj = new JSONObject();
        paramsobj.put("from", "0x8f600e28D0694F06A28C2edD74F2f3Bb9e865EcC");
        paramsobj.put("to", "0x8f600e28D0694F06A28C2edD74F2f3Bb9e865EcC");
        paramsobj.put("data", "0x"+HexToString.stringtohex(inputString));
        params.put(paramsobj);
        sendobj.put("params", params);
		sendobj.put("id", 100);

		System.out.println("==========sendTransaction body data==========");
		System.out.println(sendobj);
		System.out.println();
		
	
		String result = fs.sendPost("http://localhost:8545/",sendobj.toString());
		//보낸 결과값
		System.out.println("==========sendTransaction result data==========");
		System.out.println(result);
		System.out.println();


		JSONObject sendtransobj = new JSONObject(result);
		sendobj = new JSONObject();
        sendobj.put("jsonrpc", "2.0");
        sendobj.put("method", "eth_getTransactionByHash");
		params = new JSONArray();
		params.put(sendtransobj.getString("result"));
        sendobj.put("params", params);
		sendobj.put("id", 100);

		System.out.println("==========getTransaction body data==========");
		System.out.println(sendobj);
		System.out.println();
		
		result = fs.sendPost("http://localhost:8545/",sendobj.toString());
		System.out.println("==========getTransaction result data==========");
		System.out.println(result);
		System.out.println();
		
		String input = new JSONObject(result).getJSONObject("result").getString("input");
		input = input.substring(2,input.length());
		
		System.out.println("==========getTransaction result json hex data==========");
		System.out.println(input);
		System.out.println();
		
		String rresult = HexToString.hextostring(input);
		rresult = rresult.substring(2,rresult.length());

		JSONObject resultobj = new JSONObject(rresult);
		System.out.println("==========json hex data To String==========");
		System.out.println(resultobj.toString());
		System.out.println();



		if(extractFileHashSHA256(new File("c:\\data\\text.txt").toString()).equals(resultobj.get("title").toString())){
			System.out.println("text equal");
		}
		if(extractFileHashSHA256(new File("c:\\data\\image.jpg").toString()).equals(resultobj.get("picture").toString())){
			System.out.println("image equal");
		}
		if(extractFileHashSHA256(new File("c:\\data\\video.avi").toString()).equals(resultobj.get("video").toString())){
			System.out.println("video equal");
		}
		
		
		
	}
	
}
