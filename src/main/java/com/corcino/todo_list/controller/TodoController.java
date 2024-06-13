package com.corcino.todo_list.controller;

import com.corcino.todo_list.dto.TodoRequest;
import com.corcino.todo_list.dto.TodoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @PostMapping
    public ResponseEntity<TodoResponse> post(@RequestBody TodoRequest todoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("{todoId}")
    public ResponseEntity<TodoResponse> put(@PathVariable("todoId") Long todoId, TodoRequest todoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("{todoId}")
    public ResponseEntity<TodoResponse> delete(@PathVariable Long todoId) {
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAll() {
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("{todoId}")
    public ResponseEntity<TodoResponse> getById(@PathVariable Long todoId) {
        return ResponseEntity.status(200).body(null);
    }

}
