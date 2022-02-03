package com.github.jskov.dao.jdbc;

import com.github.jskov.model.Expense;
import com.github.jskov.testdb.SpringJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({ExpenseDaoJdbc.class})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
public class ExpenseDaoJdbcTestIT {

    private ExpenseDaoJdbc expenseDaoJdbc;

    public ExpenseDaoJdbcTestIT(@Autowired ExpenseDaoJdbc expenseDaoJdbc) {
        this.expenseDaoJdbc = expenseDaoJdbc;
    }

    @Test
    void testAddNewExpense() {

        assertNotNull(expenseDaoJdbc);
        int countOfExpensesBefore = expenseDaoJdbc.countOfExpenses();

        Expense expense = new Expense();
        expense.setDateOfExpense(LocalDate.of(2021, 01, 03));
        expense.setCategoryId(1);
        expense.setSumOfExpense(new BigDecimal("1.5"));

        Integer expenseId = expenseDaoJdbc.addNewExpense(expense);
        assertNotNull(expenseId);
        int countOfExpensesAfter = (expenseDaoJdbc.countOfExpenses());
        assertEquals(countOfExpensesBefore, countOfExpensesAfter - 1);
    }

    @Test
    void testCountOfExpenses() {

        assertNotNull(expenseDaoJdbc);
        Integer countExpense = expenseDaoJdbc.countOfExpenses();
        assertNotNull(countExpense);

        Expense expense = new Expense();
        expense.setDateOfExpense(LocalDate.of(2021, 01, 03));
        expense.setCategoryId(1);
        expense.setSumOfExpense(new BigDecimal("1.5"));

        expenseDaoJdbc.addNewExpense(expense);
        Integer countExpenseAfterAdd = expenseDaoJdbc.countOfExpenses();
        assertNotNull(countExpenseAfterAdd);
        assertEquals(countExpense + 1, countExpenseAfterAdd);
    }

}
