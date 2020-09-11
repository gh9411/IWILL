package com.project.service.user;

import javax.validation.Valid;

import com.project.model.user.UserEntity;

public interface UserService {
	public UserEntity login(String email, String password);

	public int register(UserEntity user);

	public UserEntity detail(String email);

	public int modify(@Valid UserEntity user);

	public int delete(int uid);

	public UserEntity findpw(String email);
	
}
