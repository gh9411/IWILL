package com.project.service.will;

import java.util.List;

import com.project.dao.will.WillDAO;
import com.project.model.will.WillEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WillService {

    @Autowired
    WillDAO willDao;
    
    
    public List<WillEntity> findAll(){
        return willDao.findAll();
    }

    public void register(WillEntity will){

    }

    public Object save(WillEntity newWill){ // 새로운 유언장 저장

        return willDao.save(newWill);
    }

    public List<WillEntity> getWillByUid(String uid){
        return willDao.findAllByUid(uid);
    }
}
