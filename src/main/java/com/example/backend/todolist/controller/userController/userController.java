package com.example.backend.todolist.controller.userController;

import com.example.backend.todolist.dto.signupDto;
import com.example.backend.todolist.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class userController {

    private final userService userService;

    @PostMapping("/user/signup")
    public String signup(@RequestBody signupDto signupDto){
        if(userService.duplicateCheck(signupDto.getUsername())){ //아이디 중복체크
            userService.join(signupDto); //중복되지 않는 아이디라면, 회원가입.
        }
        else{
            throw new RuntimeException("동일한 ID가 존재합니다. 다른아이디로 진행해주십시오.");
        }
        return "OK";
    }

    @PostMapping("/user/login")
    public String LogIn(@RequestBody signupDto signupDto, HttpServletRequest request){
        String username = userService.login(signupDto.getUsername(),signupDto.getPassword());
        HttpSession session = request.getSession(); //기본값 true, 있다면 기존세션 반환 없다면 생성.
        session.setAttribute("username",username); //세션정보로 사용자의 ide를 들고있음. 회원가입때 검증로직으로 중복되지않을것.
        return "OK";
    }

    @PostMapping("/user/logout")
    public String LogOut(HttpServletRequest request){
        //login요청을 한 이후에는 , postman에서는 자동으로 header에 쿠키를 추가를 해줍니다.
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate(); //세션저장소에서 삭제해야함.
            return "OK";
        }
        return "Fail";
    }
}
