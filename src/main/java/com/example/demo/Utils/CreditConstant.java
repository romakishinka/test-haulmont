package com.example.demo.Utils;

import lombok.Getter;

@Getter
public enum CreditConstant {
    AUTO_CREDIT("Автокредит", 3000000),
    MORTGAGE_CREDIT("Ипотечный", 5000000),
    CONSUMER_CREDIT("Потребительский", 500000);

    private String name;
    private int limit;

    CreditConstant(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }
}
