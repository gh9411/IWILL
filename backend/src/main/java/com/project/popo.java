package com.project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class popo {
    public static void main(String[] args) {
        String sendDate = "2020.09.28-17.34";

        Calendar cal = Calendar.getInstance();
        
        
        

        Date date = new Date();

        SimpleDateFormat sdp = new SimpleDateFormat("dd");

        String str = sdp.format(date);
        System.out.println(str);


        // System.out.println(date.toString());
    }
}
