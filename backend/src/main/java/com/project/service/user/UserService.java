package com.project.service.user;

import java.util.List;

import javax.validation.Valid;

import com.project.model.user.UserEntity;

public interface UserService {
	public List<UserEntity> login(String email, String password);

	public void register(UserEntity user);

	public List<UserEntity> detail(String email);

	public void modify(UserEntity user);

	public void delete(int uid);

	public List<UserEntity> findpw(String email);
	
}
