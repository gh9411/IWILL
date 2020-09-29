package com.project.model.will.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WillCreateDTO {
    int wid;// 유언장 아이디
    String uid;// 사용자 아이디

    String title;//제목

    String content;//내용
    MultipartFile video;//비디오
    MultipartFile image;//이미지

    String witness; //증인
    String receive; //이메일 받을사람
    String senddate; // 이메일 전송 날짜
    
}
