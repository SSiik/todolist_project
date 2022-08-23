package com.example.backend.todolist.enttiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class subBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username; //user의 ide와 매핑될것.

    @Column @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private board board;

    public subBoard(String username, String content, board board) {
        this.username = username;
        this.content = content;
        this.board = board;
    }
}
