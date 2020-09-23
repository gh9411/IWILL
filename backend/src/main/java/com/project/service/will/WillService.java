package com.project.service.will;

import java.util.List;

import com.project.dao.will.WillDAO;
import com.project.model.will.WillEntity;
import com.project.util.HexToString;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WillService {
    WillDAO willdao;

    public List<WillEntity> findAll() {
        return willdao.findAll();
    }

    public void register(WillEntity will){
        willdao.save(will);
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
