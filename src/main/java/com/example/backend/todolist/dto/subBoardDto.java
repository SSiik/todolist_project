package com.example.backend.todolist.dto;


import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class subBoardDto {

    private Long id;

    @Nullable
    private Long subBoard_id;

    private String content;

    public subBoardDto(Long id, @Nullable Long subBoard_id, String content) {
        this.id = id;
        this.subBoard_id = subBoard_id;
        this.content = content;
    }
}
