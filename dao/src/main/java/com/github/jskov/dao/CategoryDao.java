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
}
