package com.project.service.will;

import java.util.List;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;
import com.project.model.will.WillEntity;

@Service
public class FileService {
    @Autowired
    FileDAO filedao;

    public List<WillEntity> findAll(){
        return filedao.findAll();
    }

    public void upload(final MultipartFile multipartFile){
        //1. 파일 업로드

        final File targetFile = new File("/" + multipartFile.getOriginalFilename()); // 저장 위치 입력
		try {
			final InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
		} catch (final IOException e) {
			FileUtils.deleteQuietly(targetFile);
			e.printStackTrace();
        }
        // 해쉬값 추출
        String filehash = checkHash(targetFile);
        
        // 블록체인에 해쉬값 저장
        

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
}
