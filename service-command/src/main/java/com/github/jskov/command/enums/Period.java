package com.github.jskov.command.enums;

public enum Period {
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    YEAR("year"),
    ALL("all");

    private String periodName;

    Period(String periodName) {
        this.periodName = periodName;
    }

    public String getPeriodName() {
        return periodName;
    }
}
