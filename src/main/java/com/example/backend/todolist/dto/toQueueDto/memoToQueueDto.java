package com.example.backend.todolist.dto.toQueueDto;

import lombok.Data;

@Data
public class memoToQueueDto {
    private Long id;
    private String content;
    private String username;
    private int status;
}
