package com.project.service.will;

import javax.persistence.Entity;

import com.project.dao.will.WilllogDAO;
import com.project.model.will.WillEntity;
import com.project.model.will.WillLogEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WillLogService {
    @Autowired
    WilllogDAO dao;


    public void register(WillEntity entity) throws Exception{

        WillLogEntity willlogentity = new WillLogEntity(entity);

        dao.save(willlogentity);
    }

    
}
