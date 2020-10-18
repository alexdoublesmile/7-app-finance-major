package com.plohoy.financemajor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public enum Category {
    SPORT("sport"),
    FOOD("food");

    @Getter
    private String name;
    private static final List<Category> categories = Arrays.asList(Category.values());

    public static Category findByName(String name) {
        for (Category value : Category.values()) {
            if (name.equals(value.getName()))
            return value;
        }
        return null;
    }

    public static Category findByNumber(int number) {
        return categories.get(number);
    }
}
