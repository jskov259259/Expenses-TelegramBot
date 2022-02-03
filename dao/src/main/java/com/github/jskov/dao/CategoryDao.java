package com.github.jskov.dao;

import com.github.jskov.model.Category;

import java.util.List;

/**
 * Category DAO Interface.
 */
public interface CategoryDao {

    /**
     * Get all categories
     * @return Category list
     */
    List<Category> findAllCategories();

    /**
     * Create new Category
     * @param category - category to create
     * @return - id of the created category
     */
    Integer addNewCategory(Category category);

    /**
     * Check unique category name
     * @param categoryName - Category name
     * @return - true if the category is missing
     */
    boolean isCategoryUnique(String categoryName);
}
