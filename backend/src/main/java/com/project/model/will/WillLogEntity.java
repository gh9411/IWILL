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
@Table(name = "willlog")
public class WillLogEntity {
    
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx; // 인덱스

    @Column(name="wid")
    int wid; // 유언장 번호 (같으면 인덱스 높은 순)

    @Column(name="uid")
    String uid; 

    @Column(name="title")
    String title; 

    @Column(name="filepath")
    String filepath; 

    @Column(name="transactionhash")
    String transactionhash; 

    @Column(name="receive")
    String receive; 

    @Column(name="createdate")
    String createdate;

    @Column(name="islawed")
    @ColumnDefault("0") //default 0
    int islawed; 

    @Column(name="witness")
    String witness; 

    @Column(name="senddate")
    String senddate;

    public WillLogEntity(WillEntity entity){
        this.wid = entity.getWid();
        this.uid = entity.getUid();
        this.title = entity.getTitle();
        this.filepath = entity.getFilepath();
        this.transactionhash = entity.getTransactionhash();
        this.receive = entity.getReceive();
        this.createdate = entity.getCreatedate();
        this.islawed = entity.getIslawed();
        this.witness = entity.getWitness();
        this.senddate = entity.getSenddate();
    }
}
