package me.tonoy.recapspringboot.todos;

import java.util.Optional;

public interface TodoService {
    Optional<TodoDto> getTodoByIdAndCreateBy(String todoId, String createdBy);

    TodoDto createTodo(TodoCreateUpdateDto todo, String createdBy);
}
