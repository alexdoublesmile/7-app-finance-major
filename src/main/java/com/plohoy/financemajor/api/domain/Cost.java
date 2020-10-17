package com.plohoy.financemajor.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class Cost {
    private ObjectId id;
    private Category category;
    private long amount;
    private boolean isIncome;
}
