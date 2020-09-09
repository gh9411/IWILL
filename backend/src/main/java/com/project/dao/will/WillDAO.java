package com.project.dao.will;


import com.project.model.will.WillEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WillDAO extends JpaRepository<WillEntity, Long>{
    public WillEntity registerWill(WillEntity will);
}
