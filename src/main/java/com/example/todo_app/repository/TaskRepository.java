package com.example.todo_app.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.todo_app.domain.Category;
import com.example.todo_app.domain.Task;

@Repository
public class TaskRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TaskRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private final RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setTitle(rs.getString("title"));
        Category category = new Category();
        category.setCategory(rs.getString("category"));
        task.setCategory(category);
        return task;

    };

    public List<Task> findAll() {
        String sql = """
                    SELECT t.id, t.title, t.category_id
                    FROM tasks t
                    INNER JOIN categories c ON t.category_id = c.id
                """;
        return namedParameterJdbcTemplate.query(sql, taskRowMapper);
    }

    public void insert(Task task) {
        String sql = "INSERT INTO tasks (title, category_id) VALUES (:title, :categoryId)";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(task));
    }

    public void update(Task task) {
        String sql = """
                  UPDATE bucket
                    SET title=:title, category_id=:category_id, category_id=:categoryId, user_id=:userId
                    WHERE id=:id
                """;
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(task));
    }

    public void delete(Integer taskId) {
        String sql = "DELETE FROM tasks WHERE id = :taskId";
        MapSqlParameterSource params = new MapSqlParameterSource("taskId", taskId);
        namedParameterJdbcTemplate.update(sql, params);
    }
}