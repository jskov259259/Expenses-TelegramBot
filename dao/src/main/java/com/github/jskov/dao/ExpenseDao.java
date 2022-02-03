package com.github.jskov.dao;

import com.github.jskov.model.Expense;

/**
 * Expense DAO Interface.
 */
public interface ExpenseDao {

    /**
     * Create new Expense
     * @param expense - expense to create
     * @return - id of the created expense
     */
    Integer addNewExpense(Expense expense);

    /**
     * Get count of all expenses
     * @return - count of all expenses
     */
    Integer countOfExpenses();
}
