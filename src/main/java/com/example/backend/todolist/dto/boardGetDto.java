package com.example.backend.todolist.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class boardGetDto {
    private Long id;
    private String username;
    private String content;
    private String status;
    private List<subBoardDto> subs;

    public boardGetDto(Long id, String username, String content, String status) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.status = status;
    }
}
