package com.project;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;

import com.project.model.will.WillEntity;
import com.project.service.will.WillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    @Autowired
    WillService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        checkDateForSendEmail ct = new checkDateForSendEmail();
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
                                service.sendEmail(email);
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


