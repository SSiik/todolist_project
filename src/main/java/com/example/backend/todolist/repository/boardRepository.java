package com.example.backend.todolist.repository;

import com.example.backend.todolist.enttiy.board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface boardRepository extends JpaRepository<board,Long> {
    Optional<board> findByIdAndUsername(Long id,String username);
    void deleteByIdAndUsername(Long id,String username);
}
