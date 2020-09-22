package com.project.dao.will;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.project.model.will.WillEntity;

public interface FileDAO extends JpaRepository<WillEntity, Long>{
    //Get all File
    public List<WillEntity> findAll();

    //Get all File of written by certain user
    //public List<WillEntity> findByUserId(String userId);

    //public WillEntity findReviewById(long id);


}