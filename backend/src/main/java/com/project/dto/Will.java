package com.project.dto;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Will {
    int uid;//유언장 주인
    String content; // 유언장 내용
    String receiver;//받을사람
    String will;//유언장 해쉬값
}
