package com.project.dao.user;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.user.UserEntity;

public interface UserDAO extends JpaRepository<UserEntity, Long> {

	public List<UserEntity> login(String email, String password);

	void register(UserEntity user);

	List<UserEntity> detail(String email);

	void modify(UserEntity user);

	void delete(int uid);

	List<UserEntity> findpw(String email);

}
