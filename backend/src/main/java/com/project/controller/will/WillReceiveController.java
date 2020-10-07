package com.project.controller.will;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.project.model.will.WillEntity;
import com.project.model.will.WillReceiveEntity;
import com.project.service.will.WillReceiveService;
import com.project.service.will.WillService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = { "*" },maxAge = 6000)

@RestController
public class WillReceiveController {
    
    @Autowired
    WillReceiveService willreceiveservice;

    @Autowired
    WillService willservice;

    @GetMapping(value = "/will/receive")
    public Object getAllreceivewill(String ruid){

        List<WillReceiveEntity> list = willreceiveservice.searchAll(ruid);


        return new ResponseEntity<Object>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/will/savetransaction")
    public Object savetransaction(String hash,String ruid) throws Exception{
        System.out.println("savetransaction");

        WillEntity will =  willservice.getByTransactionHash(hash);
        willreceiveservice.receivewillRegistser(ruid, will);

        List<WillReceiveEntity> list =  willreceiveservice.searchAll(ruid); 
        
        ArrayList<HashMap<String,String>> result = new ArrayList<>();
        

        for(WillReceiveEntity willentity : list){
            HashMap<String,String> hm = new HashMap<>();
            JSONObject jsonObj = new JSONObject(willentity.getFilepath());

            FileInputStream input=new FileInputStream(jsonObj.getString("textpath"));
            InputStreamReader reader=new InputStreamReader(input,"UTF-8");
            BufferedReader in = new BufferedReader(reader);
            
            String line = "";
            String tresult ="";
            if((line=in.readLine()) != null){
                tresult += line + System.lineSeparator();
            }
            System.out.println(tresult);

            hm.put("uid", willentity.getUid());
            hm.put("title", willentity.getTitle());
            hm.put("text", tresult);
            hm.put("image","http://j3a104.p.ssafy.io/images/"+jsonObj.getString("imagepath").substring(18));
            hm.put("video","http://j3a104.p.ssafy.io/images/"+jsonObj.getString("videopath").substring(18));
            result.add(hm);
        }



        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

}
