package com.project.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long uid; // uid
	
	@Column(name = "upw")
    String upw; // passwd
	
	@Column(name = "email")
    String email; // email
	
	@Column(name = "name")
    String name;  // 이름
	
	@Column(name = "phone")
    String phone; // phone
	
	@Column(name = "address")
    String address; // address
	
	@Column(name = "createDate")
    String createDate; // 생성일
	
	@Column(name = "sendDate")
    String sendDate;
	
	@Column(name = "profile")
    String profile;    //
	
	@Column(name = "usertype")
    int usertype;      // 회원 유형
}