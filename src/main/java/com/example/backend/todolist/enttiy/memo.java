package com.example.backend.todolist.enttiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class memo extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username; //user의 ide와 매핑될것.

    @Column @Lob
    private String content;

    public memo(String username, String content) {
        this.username = username;
        this.content = content;

    }
}
