package com.corcino.todo_list.dto;

import lombok.Data;

@Data
public class TodoRequest {

    private String title;
    private String description;
    private boolean completed;

}
