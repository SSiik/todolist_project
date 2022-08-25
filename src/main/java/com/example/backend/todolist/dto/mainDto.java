package com.example.backend.todolist.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class mainDto {
    private String username;
    private char rank;
    private List<boardGetDto> isCleared = new ArrayList<>();
    private List<boardGetDto> isExpired = new ArrayList<>();
    private List<boardGetDto> isNow = new ArrayList<>(); //객체 생성 즉시 생성.
}
