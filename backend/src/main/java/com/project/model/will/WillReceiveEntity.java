package com.project.model.will;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receivewill")

public class WillReceiveEntity {
    @Id
    @Column(name = "idx")
    int idx;

    
    @Column(name = "ruid")
    String ruid;

    @Column(name="wid")
    int wid;

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

    

    public WillReceiveEntity(String ruid,WillEntity entity){
        this.ruid = ruid;
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
