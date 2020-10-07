package com.project.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.project.model.user.UserEntity;
import com.project.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class UserController {

	@Autowired
	UserService userservice;

	@PostMapping("/user/login")
	@ApiOperation(value = "로그인", notes = "로그인 입니다.")
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "사용자 E-mail", required = true, dataType = "string"),
			@ApiImplicitParam(name = "upw", value = "사용자 Password", required = true, dataType = "string") })
	public Object login(@RequestParam String email, @RequestParam String password) {
		return userservice.login(email, password);
	}

	@RequestMapping("/user/logout")
	@ApiOperation(value = "로그아웃", notes = "로그아웃 입니다.")
	public String logout(HttpSession session) {
		return "/user/login";
	}

	@PostMapping("/user/signup")
	@ApiOperation(value = "회원가입", notes = "회원 가입 입니다.")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "uid", value = "사용자 UID", required = true, dataType = "int"),
			@ApiImplicitParam(name = "upw", value = "사용자 Password", required = true, dataType = "string"),
			@ApiImplicitParam(name = "accounthash", value = "사용자 hash", required = true, dataType = "string"),
			@ApiImplicitParam(name = "email", value = "사용자 E-mail", required = true, dataType = "string"),
			@ApiImplicitParam(name = "name", value = "사용자 이름", required = true, dataType = "string"),
			@ApiImplicitParam(name = "phone", value = "연락처", required = true, dataType = "string"),
			@ApiImplicitParam(name = "createdate", value = "생성 날짜", required = true, dataType = "string"),
			@ApiImplicitParam(name = "senddate", value = "전송 날짜", required = true, dataType = "string"),
			@ApiImplicitParam(name = "profile", value = "자기 소개", required = true, dataType = "string"),
			@ApiImplicitParam(name = "usertype", value = "회원 유형", required = true, dataType = "int"), })
	public Object signup(UserEntity user) throws Exception {
		
		return userservice.signup(user);
	}

	@PostMapping("/user/detail")
	@ApiOperation(value = "회원 정보", notes = "회원 정보 조회 입니다.")
	@ApiImplicitParam(name = "email", value = "사용자 E-mail", required = true, dataType = "string")
	public Object detail(@RequestParam String email) {
		
		return userservice.detail(email);
	}

	@PostMapping("/user/update")
	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정 입니다.")
	@ApiImplicitParam(name = "userdto", value = "UserDto", required = true, dataType = "Object")
	public Object update(UserEntity user) {
		userservice.update(user);
		
		return userservice.detail(user.getEmail());
	}

	@PostMapping("/user/updatepw")
	@ApiOperation(value = "비밀번호 수정", notes = "비밀번호 수정 입니다.")
	@ApiImplicitParam(name = "userdto", value = "UserDto", required = true, dataType = "Object")
	public Object updatepw(@RequestParam String email, @RequestParam String upw) {
		userservice.updatepassword(email, upw);

		return userservice.detail(email);
	}


	@PostMapping("/user/delete")
	@ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴 입니다.")
	@ApiImplicitParam(name = "uid", value = "사용자 uid", required = true, dataType = "int")
	public void delete(@RequestParam int uid) {
		userservice.delete(uid);
	}

	@PostMapping("/user/findPw")
	@ApiOperation(value = "비밀번호 찾기", notes = "비밀번호 찾기 입니다.")
	public Object findpw(@RequestParam String email) {
		
		return userservice.findpw(email);
	}
}