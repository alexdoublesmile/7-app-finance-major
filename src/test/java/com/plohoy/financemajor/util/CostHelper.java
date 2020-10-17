package com.plohoy.financemajor.util;

import com.plohoy.financemajor.api.domain.Category;
import com.plohoy.financemajor.api.domain.Cost;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CostHelper {

    public Cost getRandomCost() {
        return new Cost(
                null,
                Category.findByNumber(
                        getRandomFrom(Category.values().length)),
                getRandomFrom(10000),
                getRandomFrom(2) == 1 ? false : true);
    }

    private int getRandomFrom(int max) {
        return (int) (Math.random() * max);
    }
}
