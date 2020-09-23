package com.project.model.user;

import javax.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "user")
public class UserEntity {
    
	@Id
	private String uid; // uid
	private String upw; // passwd
	private String accounthash;
	private String email; // email
	private String name;  // 이름
	private String phone; // phone
	private String createDate; // 생성일
	private String sendDate;
	private String profile;    //
	private int usertype;      // 회원 유형
	
	public UserEntity() {};
	
	public UserEntity(String uid, String upw, String accounthash, String email, String name, String phone,
			String createDate, String sendDate, String profile, int usertype) {
		super();
		this.uid = uid;
		this.upw = upw;
		this.accounthash = accounthash;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.createDate = createDate;
		this.sendDate = sendDate;
		this.profile = profile;
		this.usertype = usertype;
	}
	
	
}