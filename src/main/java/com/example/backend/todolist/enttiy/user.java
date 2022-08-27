package com.example.backend.todolist.enttiy;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

@Data
@Document(indexName = "user")
public class user {

    @Id
    private String id;

    private String username;

    private String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
