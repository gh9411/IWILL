package com.project.controller.will;

import java.util.HashMap;
import java.util.List;

import java.util.List;

import javax.validation.Valid;

import com.project.model.will.WillEntity;
import com.project.model.will.dto.WillCreateDTO;
import com.project.service.will.FileService;
import com.project.service.will.WillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = { "*" },maxAge = 6000)

@RestController
public class WillController {
    
    @Autowired
    WillService willservice;

    @Autowired
    FileService fileservice;
    

    @PostMapping("/will/register")
    @ApiOperation(value = "유언장 작성", notes = "유언장 작성 기능입니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
            @ApiImplicitParam(name = "content", value = "유언장 내용", required = true, dataType = "string"),
            @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
            @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
            @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
    })
    public ResponseEntity<Object> register(WillCreateDTO willdto) throws Exception{

        final String UID = willdto.getUid();
        final String TITLE = willdto.getTitle();
        final String TRANSKEY; // json send transaction key
        final String TEXTPATH; // text file path
        final String VIDEOPATH; //  video file path
        final String IMAGEPATH; // image file path

        HashMap<String,String> videohm =  fileservice.upload(willdto.getVideo());
        HashMap<String,String> imagehm = fileservice.upload(willdto.getImage());
        HashMap<String,String> texthm = fileservice.uploadtxt(willdto.getContent());

        TEXTPATH = texthm.get("path");
        VIDEOPATH = videohm.get("path");
        IMAGEPATH = imagehm.get("path");
        TRANSKEY = willservice.sendTransaction(imagehm.get("hash"), videohm.get("hash"), texthm.get("hash"));

        // willservice.register(will);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/will/read")
    @ApiOperation(value = "유언장 읽기", notes = "유언장 읽기 기능입니다.")
    @ApiImplicitParams({
            // @ApiImplicitParam(name = "uid", value = "유언장 주인", required = true, dataType = "int"),
            // @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
    })
    public ResponseEntity<Object> read(){
    // public ResponseEntity<Object> read(@Valid @RequestBody WillEntity will){
        List<WillEntity> list = willservice.findAll();
        for(WillEntity will : list){
            System.out.println(will);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @GetMapping("/will/all")
    public Object getAllWills(){
        List<WillEntity> wills = willservice.findAll();
        return new ResponseEntity<List<WillEntity>>(wills, HttpStatus.OK);
    }
    
     //Read wills by id 
     @GetMapping(value = "/will/user/{userId}")
     public Object getReviewByUserId(@PathVariable String userId){
         List<WillEntity> wills = willservice.getWillByUid(userId);
         return new ResponseEntity<List<WillEntity>>(wills, HttpStatus.OK);
     }
     

     @GetMapping(value = "/will/recent/{userId}")
     public Object getRecentReviewByUserId(@PathVariable String userId){
         WillEntity will = willservice.getRecentWillByUid(userId);
         return new ResponseEntity<WillEntity>(will, HttpStatus.OK);
     }

}
