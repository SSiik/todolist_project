package com.example.backend.todolist.service;

import com.example.backend.todolist.dto.boardGetDto;
import com.example.backend.todolist.dto.mainDto;
import com.example.backend.todolist.dto.subBoardDto;
import com.example.backend.todolist.enttiy.board;
import com.example.backend.todolist.enttiy.memo;
import com.example.backend.todolist.enttiy.step;
import com.example.backend.todolist.repository.boardRepository;
import com.example.backend.todolist.repository.memoRepository;
import com.example.backend.todolist.repository.subBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class boardService {

    private final boardRepository boardRepository;
    private final subBoardRepository subBoardRepository;
    private final memoRepository memoRepository;


    public mainDto getMainPage(String username) {
        Optional<List<board>> results = boardRepository.findAllFilter(username);
        mainDto mainDto = new mainDto();
        if (results.isPresent()) {
            List<board> boards = results.get();
            for (board board : boards) {
                boardGetDto boardGetDto = new boardGetDto(board.getId(), board.getUsername(), board.getContent(), "cleared");
                List<subBoardDto> subs = new ArrayList<>();
                List<step> subBoards = board.getSubBoards();
                for (step subBoard : subBoards) {
                    subs.add(new subBoardDto(board.getId(), subBoard.getId(), subBoard.getContent()));
                }
                boardGetDto.setSubs(subs);
                if(board.isNow()) mainDto.getIsNow().add(boardGetDto);
                else if (board.isExpired()) mainDto.getIsExpired().add(boardGetDto);
                else if (board.isCleared()) mainDto.getIsCleared().add(boardGetDto);
            }
            mainDto.setUsername(username);
        }
        return mainDto;
    }

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
        subBoardRepository.deleteAllByBoard(id,username);
        boardRepository.deleteByIdAndUsername(id,username); //검증됫을때삭제.
    }

    @Transactional
    public void clear(Long id, String username) {
        Optional<board> result = boardRepository.findByIdAndUsername(id, username);
        if(result.isPresent()){
            board board = result.get();
            board.setCleared(true); board.setNow(false); board.setExpired(false);
        }
    }

    public void subpost(String username, String content, Long board_id) {
        Optional<board> byId = boardRepository.findById(board_id);
        if(byId.isPresent()){
            subBoardRepository.save(new step(username,content, byId.get()));
        }
        else{
            throw new RuntimeException("상위 게시글이 존재하지않습니다.");
        }
    }

    @Transactional
    public void subpostUpdate(String username, String content, Long board_id, Long sub_id) {
        Optional<step> result = subBoardRepository.findByIdAndSubIdAndUsername(sub_id, board_id, username);
        if(result.isPresent()){
            step subBoard = result.get();
            subBoard.setContent(content);
        }
    }

    @Transactional
    public void subdeletepost(Long sub_id,String username){
        subBoardRepository.deleteByIdAndUsername(sub_id,username);
    }

    public void memopost(String username, String content) {
        memoRepository.save(new memo(username,content));
    }

    @Transactional
    public void memopostUpdate(Long id,String username, String content){
        Optional<memo> byId = memoRepository.findByIdAndUsername(id,username); //검증의 느낌도 포함.
        if(byId.isPresent()){
            memo memo = byId.get();
            memo.setContent(content);
        }
    }

    @Transactional
    public void deletememo(Long id,String username) {
        memoRepository.deleteByIdAndUsername(id,username); //검증됫을때삭제.
    }


}
