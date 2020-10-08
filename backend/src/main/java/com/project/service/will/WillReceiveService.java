package com.project.service.will;

import java.util.List;

import com.project.dao.will.WillReceiveDAO;
import com.project.model.will.WillEntity;
import com.project.model.will.WillReceiveEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WillReceiveService {
    
    @Autowired
    WillReceiveDAO willreceivedao;

    public List<WillReceiveEntity> searchAll(String ruid){
        return willreceivedao.findByRuid(ruid);
    }

    public void receivewillRegistser(String ruid,WillEntity will){


        WillReceiveEntity receivewill = new WillReceiveEntity(ruid,will);
        willreceivedao.save(receivewill);
    }

}
