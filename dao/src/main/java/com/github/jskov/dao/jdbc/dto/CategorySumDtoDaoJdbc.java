package com.github.jskov.dao.jdbc.dto;

import com.github.jskov.model.dto.CategorySumDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategorySumDtoDaoJdbc implements CategorySumDtoDao {

    private String sqlFindCategoriesWithSumOfExpenses = "SELECT c.category_name AS categoryName, sum(e.price) AS sumOfExpense FROM expense e INNER JOIN category c ON e.category_id = c.category_id WHERE date BETWEEN :dateFrom AND :dateTo GROUP BY e.category_id ORDER BY sumOfExpense";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategorySumDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CategorySumDto> findCategoriesWithSumOfExpenses(LocalDate dateFrom, LocalDate dateTo) {

        Map<String, LocalDate> paramsOfSQL = new HashMap<>();
        paramsOfSQL.put("dateFrom", dateFrom);
        paramsOfSQL.put("dateTo",  dateTo);
        List<CategorySumDto> categorySumDtoList = namedParameterJdbcTemplate.query(sqlFindCategoriesWithSumOfExpenses,
                paramsOfSQL, BeanPropertyRowMapper.newInstance(CategorySumDto.class));
        return categorySumDtoList;
    }
}
