package com.project.model.will;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "will")
public class WillEntity {
    
    @Id
    @Column(name="wid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int wid; // 유언장 번호 (같으면 인덱스 높은 순)

    @Column(name="uid")
    String uid; // 유언장 해쉬값

    @Column(name="title")
    String title; // 유언장 해쉬값

    @Column(name="filepath")
    String filepath; // 유언장 해쉬값

    @Column(name="transactionhash")
    String transactionhash; // 유언장 해쉬값

    @Column(name="receive")
    String receive; // 유언장 해쉬값

    @Column(name="createdate")
    String createdate; // 유언장 해쉬값

    @Column(name="islawed")
    @ColumnDefault("0") //default 0
    int islawed; // 유언장 해쉬값

    @Column(name="witness")
    String witness; // 유언장 해쉬값

    @Column(name="senddate")
    String senddate; // 전송날짜
}
