package com.project.util;

import java.math.BigInteger;

public class HexToString {
    public static String stringtohex(String inputString){

        byte[] inputBytes = inputString.getBytes();
        String hexString = "";
        //string -> byte.array
        for(byte b : inputBytes){
            hexString += Integer.toString((b & 0xF0) >> 4,16);
            hexString += Integer.toString(b & 0x0F,16);
        }
        return hexString;
    
    }

    public static String hextostring(String hexString){

        //hex string -> byte array
        byte[] bytes = new BigInteger(hexString,16).toByteArray();
        String hexString2 = new BigInteger(bytes).toString(16);
        byte[] hexBytes = new byte[hexString2.length()/2];
        
        int j = 0;
        for(int i = 0; i < hexString2.length(); i+=2){
            hexBytes[j++] = Byte.parseByte(hexString2.substring(i,i+2),16);
        }
        String hr = new String(hexBytes);

        return hr;
    }
}
