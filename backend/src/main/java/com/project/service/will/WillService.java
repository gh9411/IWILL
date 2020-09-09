package com.project.service.will;

import java.util.List;

import com.project.model.will.WillEntity;

public interface WillService {
    public List<WillEntity> findAll();
    public void register(WillEntity will);
}
