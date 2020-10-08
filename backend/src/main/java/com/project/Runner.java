package com.project;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;

import com.project.model.will.WillEntity;
import com.project.service.will.FileService;
import com.project.service.will.WillService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    @Autowired
    WillService service;

    @Value("${global.coinbasekey}")
    String coinbasekey;
    
    @Value("${global.coinbasepw}")
    String coinbasepw;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        checkDateForSendEmail ct = new checkDateForSendEmail();

        //auto unlock
        FileService fs = new FileService();

        System.out.println("coinbase : " + coinbasekey);

        JSONObject sendobj = new JSONObject();
        sendobj.put("jsonrpc", "2.0");
        sendobj.put("method", "personal_unlockAccount");
        JSONArray params = new JSONArray();

        params.put(coinbasekey);
        params.put("");
        params.put(0);
        sendobj.put("params", params);
		sendobj.put("id", 100);

		String result = fs.sendPost("http://localhost:8545/",sendobj.toString());
        //보낸 결과값
        System.out.println(result);



        Thread t = new Thread(ct);
        t.start();
    }

    public class checkDateForSendEmail implements Runnable{

        @Override
        public void run() {
            while(true){

                //현재 시간에서 정각의 시간까지 스레드슬립을위한 부분
                Date date = new Date();
                SimpleDateFormat sdp = new SimpleDateFormat("HH.mm");
                String[] time = sdp.format(date).split("\\.");
                
                int Hour = Integer.parseInt(time[0]);
                int Min = Integer.parseInt(time[1]);

                int timeDif = 0;
                if(Min == 0){//00분일때
                    timeDif = (24 - Hour)*1000*60 ;
                }
                else{
                    timeDif = ((23 - Hour) * 1000 * 60 * 60) + ((60 - Min) * 1000 * 60) ;
                }
                // System.out.println("Hour : " + Hour);
                // System.out.println("Min : " + Min);
                // System.out.println(timeDif);
                try{
                    Thread.sleep(timeDif);    

                    int day = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
                    
                    List<WillEntity> list = service.findAll();

                    for(WillEntity will : list) {
                        int sendday = Integer.parseInt(will.getSenddate().split("-")[0].split("\\.")[2]);
                        if(day == sendday){
                            String emails[] = will.getReceive().split("\\,");
                            System.out.println(Arrays.toString(emails));
                            for(String email : emails){
                                service.sendEmail(email,will.getTransactionhash());
                            }
                        }
                    }
                }
                catch(Exception e){
    
                }
            }
        }
    }
}


