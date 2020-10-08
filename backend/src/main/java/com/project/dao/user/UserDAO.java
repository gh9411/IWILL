package com.project.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.user.UserEntity;

public interface UserDAO extends JpaRepository<UserEntity, String> {


	Optional<UserEntity> findByEmailAndUpw(String email, String password);
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByUid(int uid);
	
	UserEntity getUserByEmail(String email);
	UserEntity getUserByUid(int uid);
}
