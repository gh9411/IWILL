package com.project.dao.will;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.project.model.will.WillEntity;

public interface FileDAO extends JpaRepository<ReviewEntity, Long>{
    //Get all File
    public List<ReviewEntity> findAll();

    //Get all File of written by certain user
    public List<ReviewEntity> findByUserId(String userId);

    public ReviewEntity findReviewById(long id);


}