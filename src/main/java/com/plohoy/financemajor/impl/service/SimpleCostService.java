package com.plohoy.financemajor.impl.service;

import com.plohoy.financemajor.api.dao.CostDao;
import com.plohoy.financemajor.api.domain.Cost;
import com.plohoy.financemajor.api.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SimpleCostService implements CostService {
    @Autowired CostDao dao;

    @Override
    public Collection<Cost> findAll() {
        return dao.findAll();
    }

    @Override
    public void insert(Cost cost) {
        dao.insert(cost);
    }
}
