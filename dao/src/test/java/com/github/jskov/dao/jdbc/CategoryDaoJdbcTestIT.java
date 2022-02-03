package com.github.jskov.dao.jdbc;

import com.github.jskov.model.Category;
import com.github.jskov.testdb.SpringJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({CategoryDaoJdbc.class})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class CategoryDaoJdbcTestIT {

    private CategoryDaoJdbc categoryDaoJdbc;

    public CategoryDaoJdbcTestIT(@Autowired CategoryDaoJdbc categoryDaoJdbc) {
        this.categoryDaoJdbc = categoryDaoJdbc;
    }

    @Test
    void testFindAllCategories() {

        assertNotNull(categoryDaoJdbc);
        assertNotNull(categoryDaoJdbc.findAllCategories());
    }

    @Test
    void testAddNewCategory() {

        assertNotNull(categoryDaoJdbc);
        int categorySizeBefore = categoryDaoJdbc.countOfCategories();
        Category category = new Category();
        category.setCategoryId(7);
        category.setCategoryName("Tickets");
        Integer categoryId = categoryDaoJdbc.addNewCategory(category);
        assertNotNull(categoryId);
        assertEquals(categorySizeBefore, categoryDaoJdbc.countOfCategories() - 1);
    }

    @Test
    void testCountOfCategories() {

        assertNotNull(categoryDaoJdbc);
        Integer countCategory = categoryDaoJdbc.countOfCategories();
        assertNotNull(countCategory);
        assertTrue(countCategory > 0);
        Category category = new Category();
        category.setCategoryId(7);
        category.setCategoryName("Tickets");
        categoryDaoJdbc.addNewCategory(category);
        Integer countCategoryAfterAdd = categoryDaoJdbc.countOfCategories();
        assertEquals(countCategory + 1, countCategoryAfterAdd);
    }

    @Test
    void testIsCategoryUnique() {

        assertNotNull(categoryDaoJdbc);
        Category category = new Category();
        category.setCategoryId(7);
        category.setCategoryName("Tickets");
        //todo make category initialization before tests
        boolean isUnique = categoryDaoJdbc.isCategoryUnique("Tickets");
        assertTrue(isUnique);
        categoryDaoJdbc.addNewCategory(category);
        boolean isNotUnique = categoryDaoJdbc.isCategoryUnique("Tickets");
        assertFalse(isNotUnique);
    }
}