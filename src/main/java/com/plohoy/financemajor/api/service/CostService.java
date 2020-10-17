package com.plohoy.financemajor.api.service;

import com.plohoy.financemajor.api.domain.Cost;

import java.util.Collection;

public interface CostService {
    Collection<Cost> findAll();
    void insert(Cost cost);
}
