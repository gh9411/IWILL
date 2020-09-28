package com.project;

import java.util.List;

import com.project.model.will.WillEntity;
import com.project.service.will.WillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    @Autowired
    WillService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<WillEntity> list =  service.findAll();
        System.out.println(list);

    }
}
