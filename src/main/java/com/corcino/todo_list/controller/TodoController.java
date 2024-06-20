package com.corcino.todo_list.controller;

import com.corcino.todo_list.dto.TodoRequest;
import com.corcino.todo_list.dto.TodoResponse;
import com.corcino.todo_list.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponse> post(@RequestBody TodoRequest todoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(todoRequest));
    }

    @PutMapping("{todoId}")
    public ResponseEntity<TodoResponse> put(@PathVariable("todoId") Long todoId, TodoRequest todoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.update(todoRequest, todoId));
    }

    @DeleteMapping("{todoId}")
    public ResponseEntity<TodoResponse> delete(@PathVariable Long todoId) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.delete(todoId));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findAll());
    }

    @GetMapping("{todoId}")
    public ResponseEntity<TodoResponse> getById(@PathVariable Long todoId) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findById(todoId));
    }

}
