package com.example.todo_app.domain;

public class Category {
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category [category=" + category + "]";
    }

}
