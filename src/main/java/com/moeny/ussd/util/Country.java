package com.moeny.ussd.util;

public enum Country {

    MALAWI("Malawi"),
    KENYA("Kenya");

    private final String description;

    Country (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
