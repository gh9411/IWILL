package com.project.service.user.Impl;

import com.project.dao.user.UserDAO;
import com.project.model.user.UserEntity;
import com.project.service.user.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userdao;
	
	@Override
	public UserEntity login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int register(UserEntity user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserEntity detail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(@Valid UserEntity user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserEntity findpw(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
