package com.example.backend.todolist.repository;

import com.example.backend.todolist.enttiy.board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface boardRepository extends JpaRepository<board,Long> {
    Optional<board> findByIdAndUsername(Long id,String username);

    void deleteByIdAndUsername(Long id,String username);

    @Query("select distinct b from board b " +
            "join fetch b.subBoards "+
            "where b.username =:username " +
            "ORDER BY b.id ASC, b.createdDate ASC")
    Optional<List<board>> findAllFilter(@Param("username") String username);

    @Query("select b from board b " +
            "where b.now = true and b.cleared = false and b.expired = false " +
            "and b.username =:username")
    Optional<List<board>> findByLoginCheck(@Param("username") String username);
}
