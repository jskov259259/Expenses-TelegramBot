package com.github.jskov.dao.jdbc;

import com.github.jskov.dao.CategoryDao;
import com.github.jskov.model.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CategoryDaoJdbc implements CategoryDao {

    private String sqlAllCategories = "SELECT c.category_id, c.category_name FROM category c ORDER BY c.category_id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategoryDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Category> findAllCategories() {
        return namedParameterJdbcTemplate.query(sqlAllCategories, new CategoryRowMapper());
    }

    private class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet resultSet, int i) throws SQLException {
            Category category = new Category();
            category.setCategoryId(resultSet.getInt("category_id"));
            category.setCategoryName(resultSet.getString("category_name"));
            return category;
        }
    }
}
