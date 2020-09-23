package com.project.service.user;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.dao.user.UserDAO;
import com.project.model.user.UserEntity;

@Service
public class UserService {

	@Autowired
	UserDAO userdao;

	public Object login(String email, String password) {
		if (email == null || password == null)
			return null;

		Optional<UserEntity> user = userdao.findByEmailAndUpw(email, password);

		return user;
	}

	public Object signup(UserEntity user) {

		UserEntity newuser = new UserEntity();
		newuser.setUid(user.getUid());
		newuser.setUpw(user.getUpw());
		newuser.setAccounthash(user.getAccounthash());
		newuser.setEmail(user.getEmail());
		newuser.setName(user.getName());
		newuser.setPhone(user.getPhone());
		newuser.setCreatedate(user.getCreatedate());
		newuser.setSenddate(user.getSenddate());
		newuser.setProfile(user.getProfile());
		newuser.setUsertype(user.getUsertype());

		userdao.save(newuser);

		return new ResponseEntity<UserEntity>(newuser, HttpStatus.OK);
	}

	public Object detail(String email) {
		UserEntity user = userdao.getUserByEmail(email);

		return user;
	}

	public void update(UserEntity user) {
		Optional<UserEntity> modify = userdao.findByUid(user.getUid());

		modify.ifPresent(selectUser -> {
			selectUser.setUpw(user.getUpw());
			selectUser.setPhone(user.getPhone());
			selectUser.setProfile(user.getProfile());

			userdao.save(selectUser);
			System.out.println("update!!");
		});

	}

	public void delete(String uid) {
		Optional<UserEntity> del = userdao.findByUid(uid);

		del.ifPresent(selectUser -> {
			userdao.delete(selectUser);
			System.out.println("delete !!");
		});
	}

	public Object findpw(String email) {
		UserEntity user = userdao.getUserByEmail(email);
		
		return user;
	}

}
