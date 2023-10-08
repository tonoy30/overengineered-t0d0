package me.tonoy.recapspringboot.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>, PagingAndSortingRepository<TodoEntity, String> {
    Optional<TodoEntity> findByIdAndCreatedBy(String id, String createdBy);

    boolean existsByIdAndCreatedBy(String id, String createdBy);

}
