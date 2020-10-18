package com.plohoy.financemajor.service;

import com.plohoy.financemajor.domain.Cost;

import java.util.List;

public interface CostService {
    List<Cost> findAll();
    void insert(Cost cost);
    int getBalance();
}
