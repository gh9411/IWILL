package com.project.service.will.Impl;

import java.util.List;

import com.project.dao.will.WillDAO;
import com.project.model.will.WillEntity;
import com.project.service.will.WillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WillServiceImpl implements WillService {
    @Autowired
    WillDAO willdao;

    @Override
    public List<WillEntity> findAll() {
        return willdao.findAll();
    }

    @Override
    public void register(WillEntity will){
        willdao.save(will);
    }
}
