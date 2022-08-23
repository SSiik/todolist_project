package com.example.backend.todolist.dto;

import lombok.Data;

import java.util.List;

@Data
public class mainDto {
    private String username;
    private char rank;
    private List<boardDto> isCleared;
    private List<boardDto> isFinished;
    private List<boardDto> isNow;
}
