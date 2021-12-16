package com.study.entity.sorting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Order {
    ASC("ASC"),
    DESC("DESC");

    String orderValue;
}
