package com.project.model.will;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Entity
public class WillEntity {
    
    @Id
    int uid;//유언장 주인

    String receiver;//받을사람
    String will;//유언장 해쉬값
}
