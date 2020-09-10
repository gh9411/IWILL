package com.project.dao.user;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.user.UserEntity;

public interface UserDAO extends JpaRepository<UserEntity, Long> {
	
	;
}
