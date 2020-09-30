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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int uid; // uid
	
	@Column(name="upw")
	String upw; // passwd
	
	@Column(name="accounthash")
	String accounthash; // 해쉬 값
	
	@Column(name="email")
	String email; // email
	
	@Column(name="name")
	String name;  // 이름
	
	@Column(name="phone")
	String phone; // phone
	
	@Column(name="createdate")
	String createdate; // 생성일
	
	@Column(name="senddate")
	String senddate;  // 공개 날짜
	
	@Column(name="profile")
	String profile;    // 사진
	
	@Column(name="usertype")
	int usertype;      // 회원 유형
	
}