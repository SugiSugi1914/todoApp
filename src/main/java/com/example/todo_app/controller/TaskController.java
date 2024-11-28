package com.example.todo_app.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_app.domain.Task;
import com.example.todo_app.service.TaskService;

@RestController // Rest API用のコントローラーであることを示す。JSON形式でデータを返す。
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping // HTTP GETリクエスト処理
    public CompletableFuture<ResponseEntity<List<Task>>> getAllTasks() {
        // CompleteableFutureを使用して非同期処理を行う
        return CompletableFuture.supplyAsync(() -> {
            // 全てのタスクを取得
            List<Task> tasks = taskService.getAllTasks();
            // ResponseEntity.ok()でHTTP 200 OKステータスと共にタスクリストを返す
            return ResponseEntity.ok(tasks);
        });
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<String>> addTask(@RequestBody Task task) {
        return CompletableFuture.runAsync(() -> taskService.addTask(task))
                // 非同期処理が完了した後の処理
                .thenApplyAsync(none ->
                // レスポンスとしてHTTP 200 OKステータスと成功メッセージを返す
                ResponseEntity.ok("タスク作成されました"));
    }

    @DeleteMapping("/{taskId}")
    public CompletableFuture<ResponseEntity<String>> deleteTask(@PathVariable Integer taskId) {
        return CompletableFuture.runAsync(() -> taskService.deleteTask(taskId))
                // 非同期処理が完了した後の処理
                .thenApplyAsync(none ->
                // レスポンスとしてHTTP 200 OKステータスと成功メッセージを返す
                ResponseEntity.ok("タスク削除成功"));
    }

    @PutMapping
    public CompletableFuture<ResponseEntity<String>> updateTask(@RequestBody Task task) {
        return CompletableFuture.runAsync(() -> taskService.update(task))
                .thenApplyAsync(none -> ResponseEntity.ok("タスクの更新成功"));
    }
}
