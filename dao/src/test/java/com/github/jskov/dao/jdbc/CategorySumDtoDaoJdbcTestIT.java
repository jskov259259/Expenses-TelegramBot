package com.github.jskov.dao.jdbc;

import com.github.jskov.dao.jdbc.dto.CategorySumDtoDaoJdbc;
import com.github.jskov.testdb.SpringJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJdbcTest
@Import({CategorySumDtoDaoJdbc.class})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
public class CategorySumDtoDaoJdbcTestIT {

    private CategorySumDtoDaoJdbc categorySumDtoDaoJdbc;

    public CategorySumDtoDaoJdbcTestIT(@Autowired CategorySumDtoDaoJdbc categorySumDtoDaoJdbc) {
        this.categorySumDtoDaoJdbc = categorySumDtoDaoJdbc;
    }

    @Test
    void testFindCategoriesWithSumOfExpenses() {

        assertNotNull(categorySumDtoDaoJdbc);
        LocalDate dateFrom = LocalDate.of(2021, 01, 01);
        LocalDate dateTo = LocalDate.of(2022, 01, 01);
        assertNotNull(categorySumDtoDaoJdbc.findCategoriesWithSumOfExpenses(dateFrom, dateTo));
    }
}
