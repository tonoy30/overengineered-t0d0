package me.tonoy.recapspringboot.todos;

import lombok.RequiredArgsConstructor;
import me.tonoy.recapspringboot.todos.dtos.TodoCreateDto;
import me.tonoy.recapspringboot.todos.dtos.TodoDto;
import me.tonoy.recapspringboot.todos.dtos.TodoUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<TodoDto> getTodoByIdAndCreateBy(String todoId, String createdBy) {
        return todoRepository.findByIdAndCreatedBy(todoId, createdBy).map(entity -> modelMapper.map(entity, TodoDto.class));
    }

    @Override
    public TodoDto createTodo(TodoCreateDto todo) {
        TodoEntity todoEntity = modelMapper.map(todo, TodoEntity.class);

        todoEntity.setTitle(todo.getTitle());
        todoEntity.setDescription(todo.getDescription());
        todoEntity.setCompleted(todo.isCompleted());
        todoEntity.setCreatedBy(todo.getCreatedBy());
        todoEntity.setLastModifiedBy(todo.getCreatedBy());

        TodoEntity savedTodoEntity = todoRepository.save(todoEntity);

        return modelMapper.map(savedTodoEntity, TodoDto.class);
    }

    @Override
    public Optional<TodoDto> updateTodo(TodoUpdateDto todo) {
        Optional<TodoEntity> maybeTodoEntity = todoRepository.findByIdAndCreatedBy(todo.getId(), todo.getCreatedBy());
        if (maybeTodoEntity.isPresent()) {
            TodoEntity todoEntity = maybeTodoEntity.get();
            todoEntity.setDescription(todo.getDescription());
            todoEntity.setTitle(todo.getTitle());
            todoEntity.setCompleted(todo.isCompleted());
            todoEntity.setLastModifiedBy(todo.getUpdatedBy());
            return Optional.of(todoEntity).map(e -> modelMapper.map(e, TodoDto.class));
        }
        return Optional.empty();
    }
}
