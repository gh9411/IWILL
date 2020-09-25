package com.project.util;

import java.math.BigInteger;

import org.json.JSONArray;
import org.json.JSONObject;

public class popopopo {


    // {
    //     "jsonrpc":"2.0",
    //     "method":"eth_sendTransaction",
    //     "params": [{
    //       "from": "0x8f600e28D0694F06A28C2edD74F2f3Bb9e865EcC",
    //       "to": "0x8f600e28D0694F06A28C2edD74F2f3Bb9e865EcC",
    //       "data": "7b22766964656f223a22766964656f222c227469746c65223a227469746c65222c2270696374757265223a2270696374757265227d"
    //     }],   
    //     "id":100
    // }


    public static void main(String[] args) {


        //senddata
        JSONObject ob = new JSONObject();
        ob.put("title", "title");
        ob.put("picture", "picture");
        ob.put("video", "video");
        //senddata to hex
        String inputString = ob.toString();
        

        JSONObject sendobj = new JSONObject();
        sendobj.put("jsonrpc", "2.0");
        sendobj.put("method", "eth_sendTransaction");
        JSONArray params = new JSONArray();
        JSONObject paramsobj = new JSONObject();
        paramsobj.put("from", "0x8f600e28D0694F06A28C2edD74F2f3Bb9e865EcC");
        paramsobj.put("to", "0x8f600e28D0694F06A28C2edD74F2f3Bb9e865EcC");
        paramsobj.put("data", "0x"+stringtohex(inputString));
        params.put(paramsobj);
        sendobj.put("params", params);
        sendobj.put("id", 100);

        System.out.println(sendobj.toString());






        String hexString = stringtohex(inputString);

        String hr = hextostring(hexString);
        System.out.println(hr);
    }

    public static String stringtohex(String inputString){

        byte[] inputBytes = inputString.getBytes();
        String hexString = "";
        //string -> byte.array
        for(byte b : inputBytes){
            hexString += Integer.toString((b & 0xF0) >> 4,16);
            hexString += Integer.toString(b & 0x0F,16);
        }
        System.out.println(hexString);
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


