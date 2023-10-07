package me.tonoy.recapspringboot.todos;

import me.tonoy.recapspringboot.todos.dtos.TodoCreateDto;
import me.tonoy.recapspringboot.todos.dtos.TodoDto;
import me.tonoy.recapspringboot.todos.dtos.TodoUpdateDto;

import java.util.Optional;

public interface TodoService {
    Optional<TodoDto> getTodoByIdAndCreateBy(String todoId, String createdBy);

    TodoDto createTodo(TodoCreateDto todo);

    Optional<TodoDto> updateTodo(String todoId, TodoUpdateDto todo);

    boolean existsByIdAndCreatedBy(String todoId, String createdBy);

    void deleteById(String todoId);
}
