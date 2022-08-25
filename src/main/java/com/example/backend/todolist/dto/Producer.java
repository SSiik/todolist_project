package com.example.backend.todolist.dto;

import com.example.backend.todolist.dto.toQueueDto.boardToQueueDto;
import com.example.backend.todolist.dto.toQueueDto.memoToQueueDto;
import com.example.backend.todolist.dto.toQueueDto.subBoardToQueueDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToBoardQueue(boardToQueueDto dto){
        this.rabbitTemplate.convertAndSend("BOARD_QUEUE",dto);
    }

    public void sendToSubBoardQueue(subBoardToQueueDto dto) {
        this.rabbitTemplate.convertAndSend("SUBBOARD_QUEUE",dto);
    }

    public void sendToMemoQueue(memoToQueueDto dto) {
        this.rabbitTemplate.convertAndSend("MEMO_QUEUE",dto);
    }
}
