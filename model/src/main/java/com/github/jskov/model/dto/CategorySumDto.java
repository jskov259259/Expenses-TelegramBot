package com.github.jskov.model.dto;

import java.math.BigDecimal;

public class CategorySumDto {

    private String categoryName;

    private BigDecimal sumOfExpense;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getSumOfExpense() {
        return sumOfExpense;
    }

    public void setSumOfExpense(BigDecimal sumOfExpense) {
        this.sumOfExpense = sumOfExpense;
    }

    @Override
    public String toString() {
        return "CategorySumDto{" +
                "categoryName='" + categoryName + '\'' +
                ", sumOfExpense=" + sumOfExpense +
                '}';
    }
}
