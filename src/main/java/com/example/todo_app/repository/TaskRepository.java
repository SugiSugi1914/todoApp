package com.example.todo_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.todo_app.domain.Task;

@Repository
public class TaskRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TaskRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private final RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setTitle(rs.getString("title"));
        task.setCategoryId(rs.getInt("category_id"));
        return task;
    };

    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        return namedParameterJdbcTemplate.query(sql, taskRowMapper);
    }

    public Optional<Task> findByTitle(String title) {
        String sql = "SELECT * FROM tasks WHERE title = :title";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", title);
        return namedParameterJdbcTemplate.query(sql, params, taskRowMapper).stream().findFirst();
    }

    public void save(Task task) {
        String sql = "INSERT INTO tasks (title, category_id) VALUES (:title, :categoryId)";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(task));
    }

    public void deleteByTitle(String title) {
        String sql = "DELETE FROM tasks WHERE title = :title";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", title);
        namedParameterJdbcTemplate.update(sql, params);
    }
}