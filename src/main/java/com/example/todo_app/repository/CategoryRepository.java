package com.example.todo_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.todo_app.domain.Category;

@Repository
public class CategoryRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategoryRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private final RowMapper<Category> categoryRowMapper = (rs, rowNum) -> {
        Category category = new Category();
        category.setCategory(rs.getString("category"));
        return category;
    };

    public List<Category> findAll() {
        String sql = "SELECT * FROM categories";
        return namedParameterJdbcTemplate.query(sql, categoryRowMapper);
    }

    public Optional<Category> findByCategoryName(String categoryName) {
        String sql = "SELECT * FROM categories WHERE category = :categoryName";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("categoryName", categoryName);
        return namedParameterJdbcTemplate.query(sql, params, categoryRowMapper).stream().findFirst();
    }

    public void save(Category category) {
        String sql = "INSERT INTO categories (category) VALUES (:category)";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(category));
    }

    public void deleteByCategoryName(String categoryName) {
        String sql = "DELETE FROM categories WHERE category = :categoryName";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("categoryName", categoryName);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
