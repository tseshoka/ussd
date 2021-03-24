package com.moeny.ussd.util;

public enum UssdEntryPoint {

    CELL_C("*147#"),
    MTN("*136#"),
    VODACOM("*111#");

    private final String description;

    UssdEntryPoint (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
