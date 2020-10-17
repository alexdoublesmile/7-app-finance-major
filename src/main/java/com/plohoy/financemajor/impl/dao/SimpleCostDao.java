package com.plohoy.financemajor.impl.dao;

import com.plohoy.financemajor.api.dao.CostDao;
import com.plohoy.financemajor.api.domain.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class SimpleCostDao implements CostDao {
    @Autowired MongoTemplate mongoTemplate;

    @Override public Collection<Cost> findAll() {
        return mongoTemplate.findAll(Cost.class);
    }

    @Override
    public void insert(Cost cost) {
        mongoTemplate.save(cost);
    }
}
