package com.example.backend.todolist.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class boardDto {

    @Nullable
    private Long id;

    private String content;
}
