package com.plohoy.financemajor.controller;

import com.plohoy.financemajor.service.CostService;
import com.plohoy.financemajor.domain.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/costs")
public class SimpleCostController implements CostController {

    @Autowired CostService service;

    @PostMapping
    @ResponseBody
    @Override public void addCost(@RequestBody Cost cost) {
        service.insert(cost);
    }

    @GetMapping("/balance")
    @ResponseBody
    @Override public Integer getBalance() {
        return service.getBalance();
    }


}
