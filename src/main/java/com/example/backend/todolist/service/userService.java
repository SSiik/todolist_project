package com.example.backend.todolist.service;

import com.example.backend.todolist.dto.signupDto;
import com.example.backend.todolist.enttiy.user;
import com.example.backend.todolist.repository.userRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class userService {
    private final userRepository userRepository;

    public boolean duplicateCheck(String id){ //아이디(ide) 중복체크 로직.
        Optional<user> byUsername = userRepository.findByUsername(id);
        if(byUsername.isPresent()) return false;
        else {
            return true;
        }
    }
    public void join(signupDto signupDto){
        user user = new user(signupDto.getUsername(),signupDto.getPassword());
        userRepository.save(user);
    }

    public String login(String ide,String password){
        Optional<user> result = userRepository.findByUsernameAndPassword(ide, password);
        log.info("로그인 요청이 들어왔습니다.");
        if(result.isPresent()) return result.get().getUsername();
        else throw new RuntimeException("일치하지 않는 로그인정보입니다.");
    }
}
