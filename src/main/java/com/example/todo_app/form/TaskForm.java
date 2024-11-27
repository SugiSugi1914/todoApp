package com.example.todo_app.form;

public class TaskForm {

    private String title;

    private Integer categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "TaskForm [title=" + title + ", categoryId=" + categoryId + "]";
    }
}
