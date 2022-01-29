package com.github.jskov.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    private Integer expenseId;

    private LocalDate dateOfExpense;

    private Integer categoryId;

    private BigDecimal sumOfExpense;

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public LocalDate getDateOfExpense() {
        return dateOfExpense;
    }

    public void setDateOfExpense(LocalDate dateOfExpense) {
        this.dateOfExpense = dateOfExpense;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getSumOfExpense() {
        return sumOfExpense;
    }

    public void setSumOfExpense(BigDecimal sumOfExpense) {
        this.sumOfExpense = sumOfExpense;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", dateOfExpense='" + dateOfExpense + '\'' +
                ", categoryId=" + categoryId +
                ", sumOfExpense=" + sumOfExpense +
                '}';
    }
}
