package com.plohoy.financemajor.service;

import com.plohoy.financemajor.dao.CostDao;
import com.plohoy.financemajor.domain.Cost;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SimpleCostService implements CostService {
    @Autowired CostDao dao;

    @Override
    public List<Cost> findAll() {
        return dao.findAll();
    }

    @Override
    public void insert(@NonNull Cost cost) {
        if (Objects.nonNull(cost.getCategory())) {
            dao.insert(cost);
        } else {
            throw new IllegalArgumentException("Category can not be empty");
        }
    }

    @Override
    public int getBalance() {
        int result = 0;
        for (Cost cost : dao.findAll()) {
            result += cost.isIncome()
                    ? cost.getAmount()
                    : 0 - cost.getAmount();
        }

        return result;
    }
}
