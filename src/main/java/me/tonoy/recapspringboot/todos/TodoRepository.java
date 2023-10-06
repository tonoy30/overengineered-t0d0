package me.tonoy.recapspringboot.todos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    Optional<TodoEntity> findByIdAndCreatedBy(String id, String createdBy);
}
