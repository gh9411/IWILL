package com.project.service.user.Impl;

import com.project.dao.user.UserDAO;
import com.project.model.user.UserEntity;
import com.project.service.user.UserService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userdao;

	@Override
	public List<UserEntity> login(String email, String password) {
		return userdao.login(email, password);
	}

	@Override
	public void register(UserEntity user) {
		userdao.register(user);
	}

	@Override
	public List<UserEntity> detail(String email) {
		return userdao.detail(email);
	}

	@Override
	public void modify(UserEntity user) {
		userdao.modify(user);
	}

	@Override
	public void delete(int uid) {
		userdao.delete(uid);
	}

	@Override
	public List<UserEntity> findpw(String email) {
		return userdao.findpw(email);
	}

}
