package com.example.backend.todolist.dto;

import com.example.backend.todolist.dto.toQueueDto.boardToQueueDto;
import com.example.backend.todolist.dto.toQueueDto.memoToQueueDto;
import com.example.backend.todolist.dto.toQueueDto.subBoardToQueueDto;
import com.example.backend.todolist.service.boardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    boardService boardService;

    @RabbitListener(queues = "BOARD_QUEUE") //board큐
    public void handler1(boardToQueueDto dto) throws JsonProcessingException {
        if(dto.getStatus()==1)
            boardService.post(dto.getUsername(),dto.getContent());
        else if(dto.getStatus()==2)
            boardService.postUpdate(dto.getBoard_id(),dto.getUsername(),dto.getContent());
        else if(dto.getStatus()==3)
            boardService.clear(dto.getBoard_id(),dto.getUsername());
        else if(dto.getStatus()==4)
            boardService.deletepost(dto.getBoard_id(),dto.getUsername());
    }

    @RabbitListener(queues = "SUBBOARD_QUEUE") //subBoard큐
    public void handler2(subBoardToQueueDto dto) throws JsonProcessingException {
        if(dto.getStatus()==1)
            boardService.subpost(dto.getUsername(), dto.getContent(), dto.getBoard_id());
        else if(dto.getStatus()==2)
            boardService.subpostUpdate(dto.getUsername(), dto.getContent(), dto.getBoard_id(), dto.getSubBoard_id());
        else if(dto.getStatus()==3)
            boardService.subdeletepost(dto.getSubBoard_id(),dto.getUsername());
    }

    @RabbitListener(queues = "MEMO_QUEUE") //subBoard큐
    public void handler3(memoToQueueDto dto) throws JsonProcessingException {
        if(dto.getStatus()==1)
            boardService.memopost(dto.getUsername(), dto.getContent());
        else if(dto.getStatus()==2)
            boardService.memopostUpdate(dto.getId(),dto.getUsername(),dto.getContent());
        else if(dto.getStatus()==3)
            boardService.deletememo(dto.getId(),dto.getUsername());
    }
}
