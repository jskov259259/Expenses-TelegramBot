package com.github.jskov.dao.jdbc.dto;

import com.github.jskov.model.dto.CategorySumDto;

import java.time.LocalDate;
import java.util.List;

/**
 * CategorySumDto DAO Interface.
 */
public interface CategorySumDtoDao {

    List<CategorySumDto> findCategoriesWithSumOfExpenses(LocalDate dateFrom, LocalDate dateTo);

}
