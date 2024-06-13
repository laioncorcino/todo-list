package com.corcino.todo_list.dto;

import lombok.Data;

@Data
public class TodoResponse {

    private Long todoResponseId;
    private String title;
    private String description;
    private boolean completed;

}
