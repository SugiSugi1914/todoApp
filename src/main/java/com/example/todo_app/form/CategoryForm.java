package com.example.todo_app.form;

public class CategoryForm {

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryForm [category=" + category + "]";
    }
}
