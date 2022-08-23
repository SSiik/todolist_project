package com.example.backend.todolist.service;

import com.example.backend.todolist.dto.mainDto;
import com.example.backend.todolist.enttiy.board;
import com.example.backend.todolist.enttiy.subBoard;
import com.example.backend.todolist.repository.boardRepository;
import com.example.backend.todolist.repository.subBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class boardService {

    private final boardRepository boardRepository;
    private final subBoardRepository subBoardRepository;


//    public mainDto getMainPage(String username) {
//        boardRepository.findAll()
//        return mainDto;
//    }

    public void post(String username, String content) {
        boardRepository.save(new board(username,content));
    }

    @Transactional
    public void postUpdate(Long id,String username, String content) {
        Optional<board> byId = boardRepository.findByIdAndUsername(id,username); //검증의 느낌도 포함.
        if(byId.isPresent()){
            board board = byId.get();
            board.setContent(content);
        }
    }

    @Transactional
    public void deletepost(Long id,String username) {
        boardRepository.deleteByIdAndUsername(id,username); //검증됫을때삭제.
        subBoardRepository.deleteAllByBoard(id);
    }

    public void subpost(String username, String content, Long board_id) {
        Optional<board> byId = boardRepository.findById(board_id);
        if(byId.isPresent()){
            subBoardRepository.save(new subBoard(username,content, byId.get()));
        }
        else{
            throw new RuntimeException("상위 게시글이 존재하지않습니다.");
        }
    }

    @Transactional
    public void subpostUpdate(String username, String content, Long board_id, Long sub_id) {
        Optional<subBoard> result = subBoardRepository.findByIdAndSubIdAndUsername(sub_id, board_id, username);
        if(result.isPresent()){
            subBoard subBoard = result.get();
            subBoard.setContent(content);
        }
    }

    @Transactional
    public void subdeletepost(Long sub_id,String username){
        subBoardRepository.deleteByIdAndUsername(sub_id,username);
    }

    public void memopost(String username, String content) {

    }

    public void memopostUpdate(String username, String content) {

    }

    public void deletememo(String username) {

    }
}
