package com.example.backend.todolist.controller.boardController;

import com.example.backend.todolist.dto.Producer;
import com.example.backend.todolist.dto.boardDto;
import com.example.backend.todolist.dto.mainDto;
import com.example.backend.todolist.dto.subBoardDto;
import com.example.backend.todolist.service.boardService;
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

    @PostMapping("board/post")
    public void putBoard(HttpServletRequest request, @RequestBody boardDto boardDto){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        if(boardDto.getId()==null)
           boardService.post(ide,boardDto.getContent());
        else
            boardService.postUpdate(boardDto.getId(),ide,boardDto.getContent());
    }

    @DeleteMapping("board/post")
    public void deleteBoard(HttpServletRequest request, @RequestBody Long id) {
        String ide = (String)request.getAttribute("username");
        boardService.deletepost(id,ide);
    }

    @PostMapping("board/subboard/post")
    public void putSubBoard(HttpServletRequest request, @RequestBody subBoardDto subBoardDto){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        if(subBoardDto.getId()==null)
            boardService.subpost(ide, subBoardDto.getContent(), subBoardDto.getId());
        else
            boardService.subpostUpdate(ide, subBoardDto.getContent(), subBoardDto.getId(), subBoardDto.getSubBoard_id() );
    }

    @DeleteMapping("board/subboard/post")
    public void deleteSubBoard(HttpServletRequest request, @RequestBody Long id) {
        String ide = (String)request.getAttribute("username");
        boardService.subdeletepost(id,ide);
    }

    @PostMapping("board/memo/post")
    public void putMemo(HttpServletRequest request, @RequestBody boardDto boardDto){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        if(boardDto.getId()==null)
            boardService.memopost(ide,boardDto.getContent());
        else
            boardService.memopostUpdate(ide,boardDto.getContent());
    }


    @DeleteMapping("board/memo/post")
    public void deleteMemo(HttpServletRequest request, @RequestBody boardDto boardDto){ //페이징 처리. 게시판 목록을 보여주는 메인페이지겠죠.
        String ide = (String)request.getAttribute("username");
        boardService.deletememo(ide);
    }

}
