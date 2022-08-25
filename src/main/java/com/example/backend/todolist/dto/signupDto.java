package com.example.backend.todolist.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class signupDto {

    @NotBlank( message = "값이 필요합니다.")
    private String username;

    @NotBlank( message = "값이 필요합니다.")
    private String password;
}
