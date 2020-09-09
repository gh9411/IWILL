package com.project.model.will;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sendwill")
public class WillEntity {
    
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int uid;//유언장 주인

    @Column(name="r_email")
    String r_email;//받을사람

    @Column(name="r_will")
    String r_will;//유언장 해쉬값
}
