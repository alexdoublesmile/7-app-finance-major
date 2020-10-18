package com.plohoy.financemajor.dao;

import com.plohoy.financemajor.dao.CostDao;
import com.plohoy.financemajor.domain.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimpleCostDao implements CostDao {
    @Autowired MongoTemplate mongoTemplate;

    @Override public List<Cost> findAll() {
        return mongoTemplate.findAll(Cost.class);
    }

    @Override
    public void insert(Cost cost) {
        mongoTemplate.save(cost);
    }
}
