package com.example.backend.todolist.repository;

import com.example.backend.todolist.enttiy.memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface memoRepository extends JpaRepository<memo,Long> {
}
