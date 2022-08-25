package com.example.backend.todolist.repository;

import com.example.backend.todolist.enttiy.step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface subBoardRepository extends JpaRepository<step,Long> {

    @Query("select b from step b where b.id =:sub_id and b.username =:username and b.board.id =:board_id")
    Optional<step> findByIdAndSubIdAndUsername(@Param("sub_id") Long sub_id,
                                               @Param("board_id") Long board_id,
                                               @Param("username") String username);

    void deleteByIdAndUsername(Long id,String username);

    @Modifying
    @Query(nativeQuery = true,value = "delete from step where board_id =:id and username =:username")
    void deleteAllByBoard(@Param("id") Long id,@Param("username") String username);
}
