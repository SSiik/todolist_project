package com.example.backend.todolist.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class memoDto {
    @Nullable
    private Long id;

    private String content;
}
