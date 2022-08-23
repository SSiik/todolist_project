package com.example.backend.todolist.dto;


import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class subBoardDto {

    private Long id;

    @Nullable
    private Long subBoard_id;

    private String content;
}
