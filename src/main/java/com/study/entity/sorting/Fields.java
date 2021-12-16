package com.study.entity.sorting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Fields {
    RATING("rating"),
    PRICE("price");

    private String fieldName;
}
