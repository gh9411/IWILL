package com.project.model.user;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    
	@Id
	@Column(name="uid")
	private String uid; // uid
	
	@Column(name="upw")
	private String upw; // passwd
	
	@Column(name="accounthash")
	private String accounthash; // 해쉬값
	
	@Column(name="email")
	private String email; // email
	
	@Column(name="name")
	private String name;  // 이름
	
	@Column(name="phone")
	private String phone; // phone
	
	@Column(name="createdate")
	private String createdate; // 생성일
	
	@Column(name="senddate")
	private String senddate;  // 공개 날짜
	
	@Column(name="profile")
	private String profile;    // 사진
	
	@Column(name="usertype")
	private int usertype;      // 회원 유형
}