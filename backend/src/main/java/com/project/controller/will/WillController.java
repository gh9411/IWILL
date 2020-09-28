package com.project.controller.will;

import java.util.List;
import java.util.Random;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;


import com.project.model.will.WillEntity;
import com.project.service.will.WillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    JavaMailSender mailSender;

    @PostMapping("/will/register")
    @ApiOperation(value = "유언장 작성", notes = "유언장 작성 기능입니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
            @ApiImplicitParam(name = "content", value = "유언장 내용", required = true, dataType = "string")
    })
    public ResponseEntity<Object> register(@Valid @RequestBody WillEntity will){
        System.out.println("1111111111111111111111111111111111");
        System.out.println(will);
        willservice.register(will);
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


    public void getEmail(){ 
        System.out.println("hello");

        // Random r = new Random();
        // int dice = r.nextInt(4589362) + 49311; //이메일로 받는 인증코드 부분 (난수)
        
        // String setfrom = "admin@gamil.com";
        // String tomail = email; // 받는 사람 이메일
        // String title = "회원가입 인증 이메일 입니다."; // 제목
        // String content =
        
        // System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
        // System.getProperty("line.separator")+
        // "안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다"
        // +System.getProperty("line.separator")+
        // System.getProperty("line.separator")+
        // " 인증번호는 " +dice+ " 입니다. "
        // +System.getProperty("line.separator")+
        // System.getProperty("line.separator")+
        // "받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용
        
        // try {
            
        //     MimeMessage message = mailSender.createMimeMessage();
        //     MimeMessageHelper messageHelper = new MimeMessageHelper(message,true, "UTF-8");

        //     messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
        //     messageHelper.setTo(tomail); // 받는사람 이메일
        //     messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
        //     messageHelper.setText(content); // 메일 내용
        //     mailSender.send(message);

        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }
        // return new ResponseEntity<>(dice,HttpStatus.OK);
    }


}
