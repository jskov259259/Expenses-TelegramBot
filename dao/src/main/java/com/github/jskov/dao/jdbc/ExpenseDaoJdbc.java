package com.github.jskov.dao.jdbc;

import com.github.jskov.dao.ExpenseDao;
import com.github.jskov.model.Expense;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExpenseDaoJdbc implements ExpenseDao {

    private String sqlAddNewExpense = "INSERT INTO expense(expense_id, date, category_id, price) VALUES (:expense_id, :date, :category_id, :price)";
    private String sqlCountOfExpenses = "SELECT count(*) FROM expense";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExpenseDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Integer addNewExpense(Expense expense) {
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("expense_id", expense.getExpenseId());
        mapParams.put("date", expense.getDateOfExpense());
        mapParams.put("category_id", expense.getCategoryId());
        mapParams.put("price", expense.getSumOfExpense());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(mapParams);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlAddNewExpense, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer countOfExpenses() {
        return namedParameterJdbcTemplate.queryForObject(sqlCountOfExpenses, new MapSqlParameterSource(), Integer.class);
    }
}
