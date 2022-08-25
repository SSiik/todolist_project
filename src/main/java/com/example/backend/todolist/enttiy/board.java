package com.example.backend.todolist.enttiy;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class board extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username; //user의 ide와 매핑될것.

    @Column @Lob
    private String content;


    @OneToMany(mappedBy = "board", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<step> subBoards = new ArrayList<>();

    @Column
    private boolean cleared;

    @Column
    private boolean expired;

    @Column
    private boolean now;

    public board(String username, String content) {
        this.username = username;
        this.content = content;
        this.cleared = false;
        this.expired = false;
        this.now = true;
    }
}
