package com.example.backend.todolist.dto.toQueueDto;

import lombok.Data;

@Data
public class subBoardToQueueDto {
    private Long board_id;
    private Long subBoard_id;
    private String username;
    private String content;
    private int status;
}
