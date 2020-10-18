package com.plohoy.financemajor.dao;

import com.plohoy.financemajor.domain.Cost;

import java.util.List;

public interface CostDao {
    List<Cost> findAll();
    void insert(Cost cost);
}
