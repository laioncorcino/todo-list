package com.corcino.todo_list.service;


import com.corcino.todo_list.dto.TodoRequest;
import com.corcino.todo_list.dto.TodoResponse;
import com.corcino.todo_list.entity.Todo;
import com.corcino.todo_list.error.exception.NotFoundException;
import com.corcino.todo_list.repository.TodoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TodoService {

    private TodoRepository todoRepository;
    private ModelMapper mapper;

    public TodoResponse create(TodoRequest todoRequest) {
        Todo todo = mapper.map(todoRequest, Todo.class);
        todoRepository.save(todo);
        return mapper.map(todo, TodoResponse.class);
    }

    public TodoResponse update(TodoRequest todoRequest, Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NotFoundException("Todo not found"));
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setCompleted(todo.isCompleted());
        todoRepository.save(todo);

        return mapper.map(todo, TodoResponse.class);
    }

    public TodoResponse delete(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NotFoundException("Todo not found"));
        todoRepository.delete(todo);
        return mapper.map(todo, TodoResponse.class);
    }

    public TodoResponse findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NotFoundException("Todo not found"));
        return mapper.map(todo, TodoResponse.class);
    }

    public List<TodoResponse> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(todo -> mapper.map(todo, TodoResponse.class))
                .collect(Collectors.toList());
    }

}
