package com.project.controller.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" },maxAge = 6000)
@RestController
public class LoginController {

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인 입니다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "email", value = "사용자 E-mail", required = true, dataType = "string"),
        @ApiImplicitParam(name = "upw", value = "사용자 Password", required = true, dataType = "string")
    })
    public ResponseEntity<Object> login(@Valid @RequestParam String email, @RequestParam String upw){
        
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @RequestMapping("/logout")
    @ApiOperation(value = "로그아웃", notes = "로그아웃 입니다.")
    public String logout(HttpSession session){
        return "/login";
    }
}