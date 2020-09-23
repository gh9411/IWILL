package com.project.model.will.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WillCreateDTO {
    String uid;

    String title;

    String content;
    MultipartFile video;
    MultipartFile image;
    
    // receive text, ## 받을사람 list 구분자로 
    
    // filepath text, ## json 파일 위치들 글/사진s/동영상
    // trasactionhash  text, ## json 해쉬 모음
    // createDate text,
    // islawed int, ## 법적 효력이 있는지 0or1
}
