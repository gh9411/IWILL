package com.project.controller;

import javax.validation.Valid;


import com.project.model.will.WillEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    

    @PostMapping("/will/register")
    @ApiOperation(value = "유언장 작성", notes = "유언장 작성 기능입니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "유언장 주인", required = true, dataType = "int"),
            @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
            @ApiImplicitParam(name = "content", value = "유언장 내용", required = true, dataType = "string")
    })
    public ResponseEntity<Object> register(@Valid @RequestBody WillEntity will){
        System.out.println(will);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/will/read")
    @ApiOperation(value = "유언장 읽기", notes = "유언장 읽기 기능입니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "유언장 주인", required = true, dataType = "int"),
            @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
    })
    public ResponseEntity<Object> read(@Valid @RequestBody WillEntity will){
        System.out.println(will);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
