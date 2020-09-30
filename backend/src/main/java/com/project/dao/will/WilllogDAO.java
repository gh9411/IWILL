package com.project.dao.will;

import java.util.List;

import com.project.model.will.WillEntity;
import com.project.model.will.WillLogEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface WilllogDAO extends JpaRepository<WillLogEntity, Long>{
    
    // @Query(value = "SELECT uid,r_email FROM sendwill", nativeQuery = true)
    public List<WillLogEntity> findAll();
    
    public List<WillLogEntity> findAllByUid(String uid);

    
    public WillLogEntity findTopByUidOrderByIdxDesc(String uid);
}
