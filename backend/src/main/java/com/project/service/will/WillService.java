package com.project.service.will;

import java.util.List;

import com.project.dao.will.WillDAO;
import com.project.model.will.WillEntity;
import com.project.util.HexToString;

import org.springframework.beans.factory.annotation.Autowired;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WillService {

    @Autowired
    WillDAO willDao;
    
    
    public List<WillEntity> findAll(){
        return willDao.findAll();
    }


    public Object save(WillEntity newWill){ // 새로운 유언장 저장

        return willDao.save(newWill);
    }

    public List<WillEntity> getWillByUid(String uid){
        return willDao.findAllByUid(uid);
    }

    public WillEntity getRecentWillByUid(String uid){
        return willDao.findTopByUidOrderByIdxDesc(uid);
    }


    public void register(WillEntity will){
        willDao.save(will);
    }

    public String sendTransaction(String image,String video,String text) throws Exception{

        FileService fs = new FileService();

        JSONObject ob = new JSONObject();
        
        ob.put("image", image);
        ob.put("video", video);
        ob.put("text", text);

        //senddata to hex
		String inputString = "0x"+ob.toString();
        
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

		String result = fs.sendPost("http://localhost:8545/",sendobj.toString());
		//보낸 결과값
        
        JSONObject sendtransobj = new JSONObject(result);
		
        return sendtransobj.getString("result");
    }
}
