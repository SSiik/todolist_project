package com.example.backend.todolist.dto;

import com.example.backend.todolist.dto.toQueueDto.boardToQueueDto;
import com.example.backend.todolist.dto.toQueueDto.memoToQueueDto;
import com.example.backend.todolist.dto.toQueueDto.subBoardToQueueDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private  ObjectMapper objectMapper;

    public void sendToBoardQueue(boardToQueueDto dto) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(dto);
        this.template.convertAndSend("test_exchange","BOARD_KEY",message);
    }

    public void sendToSubBoardQueue(subBoardToQueueDto dto) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(dto);
        this.template.convertAndSend("test_exchange","SUBBOARD_KEY",message);
    }

    public void sendToMemoQueue(memoToQueueDto dto) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(dto);
        this.template.convertAndSend("test_exchange","MEMO_KEY",message);
    }
}
