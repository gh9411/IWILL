package com.project.dao.will;

import java.util.List;

import com.project.model.will.WillEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface WillDAO extends JpaRepository<WillEntity, Long>{
    
    // @Query(value = "SELECT uid,r_email FROM sendwill", nativeQuery = true)
    public List<WillEntity> findAll();
    
    public List<WillEntity> findAllByUid(String uid);

    @Query(value = "SELECT * FROM will where wid = (select max(wid) from will)" , nativeQuery = true)
    public WillEntity findTop();

    public WillEntity getWillByWid(int wid);

    public WillEntity getWillByTransactionhash(String transactionhash);

}
