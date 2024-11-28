package com.example.todo_app.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_app.domain.Category;
import com.example.todo_app.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Category>>> getAllCategories() {
        return CompletableFuture.supplyAsync(() -> {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        });
    }

    /**
     * 新しいカテゴリを追加（非同期）
     */
    @PostMapping
    public CompletableFuture<ResponseEntity<String>> addCategory(@RequestBody Category category) {
        return CompletableFuture.runAsync(() -> categoryService.addCategory(category))
                .thenApplyAsync(aVoid -> ResponseEntity.ok("カテゴリ作成完了"));
    }

    /**
     * 特定のカテゴリを削除（非同期）
     */
    @DeleteMapping("/{categoryName}")
    public CompletableFuture<ResponseEntity<String>> deleteCategory(@PathVariable String categoryName) {
        return CompletableFuture.runAsync(() -> categoryService.deleteCategory(categoryName))
                .thenApplyAsync(aVoid -> ResponseEntity.ok("カテゴリ削除完了"));
    }

}
