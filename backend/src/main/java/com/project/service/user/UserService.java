package com.project.service.user;

import com.project.model.user.UserEntity;

public interface UserService {
	public UserEntity login(String email, String password);
	
}
