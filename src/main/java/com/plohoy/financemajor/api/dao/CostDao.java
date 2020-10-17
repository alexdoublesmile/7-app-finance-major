package com.plohoy.financemajor.api.dao;

import com.plohoy.financemajor.api.domain.Cost;

import java.util.Collection;

public interface CostDao {
    Collection<Cost> findAll();
    void insert(Cost cost);
}
