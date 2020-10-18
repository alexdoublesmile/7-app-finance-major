package com.plohoy.financemajor.controller;

import com.plohoy.financemajor.domain.Cost;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface CostController {

    @PostMapping
    void addCost(Cost cost);

    @GetMapping
    Integer getBalance();
}
