package com.project.dao.will;


import java.util.List;

import javax.persistence.JoinColumn;

import com.project.model.will.WillEntity;
import com.project.model.will.WillReceiveEntity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface WillReceiveDAO extends JpaRepository<WillReceiveEntity, Long>{
    
    
    public List<WillReceiveEntity> findByRuid(String ruid);
    
}
