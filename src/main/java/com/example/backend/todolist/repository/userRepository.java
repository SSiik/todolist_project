package com.example.backend.todolist.repository;

import com.example.backend.todolist.enttiy.user;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends ElasticsearchRepository<user,String> {
    Optional<user> findByUsername(String name); //이제 username이라는 column을 name으로 찾는다.
    Optional<user> findByUsernameAndPassword(String name,String password);
}
