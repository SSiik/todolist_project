package com.example.backend.todolist.repository;

import com.example.backend.todolist.enttiy.board;
import com.example.backend.todolist.enttiy.memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface memoRepository extends JpaRepository<memo,Long> {
    Optional<memo> findByIdAndUsername(Long id, String username);
    void deleteByIdAndUsername(Long id,String username);
}
