package com.example.backend.todolist.controller.boardController;

import com.example.backend.todolist.dto.*;
import com.example.backend.todolist.dto.toQueueDto.boardToQueueDto;
import com.example.backend.todolist.dto.toQueueDto.memoToQueueDto;
import com.example.backend.todolist.dto.toQueueDto.subBoardToQueueDto;
import com.example.backend.todolist.service.boardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController @RequiredArgsConstructor
public class boardController {
    private final boardService boardService;
    private final Producer producer;
    private final ObjectMapper objectMapper;


    @GetMapping("board/post")
    public ResponseEntity<mainDto> getBoard(HttpServletRequest request){
        //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username"); //정보 획득.
        mainDto mainDto = boardService.getMainPage(ide);
        return new ResponseEntity<>(mainDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("board/post")
    public void putBoard(HttpServletRequest request, @RequestBody String content) throws JsonProcessingException { //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        boardToQueueDto dto = new boardToQueueDto();
        dto.setUsername(ide); dto.setContent(content); dto.setStatus(1);
        producer.sendToBoardQueue(dto);

    }

    @PostMapping("board/post")
    public void updateBoard(HttpServletRequest request, @RequestBody boardDto boardDto) throws JsonProcessingException { //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        boardToQueueDto dto = new boardToQueueDto();
        dto.setUsername(ide); dto.setBoard_id(boardDto.getId()); dto.setContent(boardDto.getContent()); dto.setStatus(2);
        producer.sendToBoardQueue(dto);
    }

    @PostMapping("board/post/cleared")
    public void clearBoard(HttpServletRequest request, @RequestBody Long id){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        boardToQueueDto dto = new boardToQueueDto();
        dto.setUsername(ide); dto.setBoard_id(id); dto.setStatus(3);
        producer.sendToBoardQueue(dto);
    }

    @DeleteMapping("board/post")
    public void deleteBoard(HttpServletRequest request, @RequestBody Long id) {
        String ide = (String)request.getAttribute("username");
        boardToQueueDto dto = new boardToQueueDto();
        dto.setUsername(ide); dto.setBoard_id(id); dto.setStatus(4);
        producer.sendToBoardQueue(dto);
    }

    @PutMapping("board/subboard/post")
    public void putSubBoard(HttpServletRequest request, @RequestBody subBoardDto subBoardDto){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        subBoardToQueueDto dto = new subBoardToQueueDto();
        dto.setBoard_id(subBoardDto.getId()); dto.setContent(subBoardDto.getContent());
        dto.setUsername(ide); dto.setStatus(1);
        producer.sendToSubBoardQueue(dto);
    }

    @PostMapping("board/subboard/post")
    public void updateSubBoard(HttpServletRequest request, @RequestBody subBoardDto subBoardDto){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        subBoardToQueueDto dto = new subBoardToQueueDto();
        dto.setBoard_id(subBoardDto.getId()); dto.setContent(subBoardDto.getContent());
        dto.setUsername(ide); dto.setStatus(2); dto.setSubBoard_id(subBoardDto.getSubBoard_id());
        producer.sendToSubBoardQueue(dto);
    }

    @DeleteMapping("board/subboard/post")
    public void deleteSubBoard(HttpServletRequest request, @RequestBody Long id) {
        String ide = (String)request.getAttribute("username");
        subBoardToQueueDto dto = new subBoardToQueueDto();
        dto.setUsername(ide); dto.setStatus(3); dto.setSubBoard_id(id);
        producer.sendToSubBoardQueue(dto);
    }

    @PutMapping("board/memo/post")
    public void putMemo(HttpServletRequest request, @RequestBody memoDto memoDto){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        memoToQueueDto dto = new memoToQueueDto();
        dto.setUsername(ide); dto.setContent(memoDto.getContent()); dto.setStatus(1);
        producer.sendToMemoQueue(dto);
    }

    @PostMapping("board/memo/post")
    public void updateMemo(HttpServletRequest request, @RequestBody memoDto memoDto){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        memoToQueueDto dto = new memoToQueueDto();
        dto.setUsername(ide); dto.setContent(memoDto.getContent()); dto.setId(memoDto.getId()); dto.setStatus(2);
        producer.sendToMemoQueue(dto);
    }


    @DeleteMapping("board/memo/post")
    public void deleteMemo(HttpServletRequest request, @RequestBody Long id){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        memoToQueueDto dto = new memoToQueueDto();
        dto.setUsername(ide); dto.setId(id); dto.setStatus(3);
        producer.sendToMemoQueue(dto);
    }

}
