package me.tonoy.recapspringboot.todos;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
    public TodoDto createTodo(TodoCreateUpdateDto todo, String createdBy) {
        TodoEntity todoEntity = modelMapper.map(todo, TodoEntity.class);

        todoEntity.setTitle(todo.getTitle());
        todoEntity.setDescription(todo.getDescription());
        todoEntity.setCompleted(todo.isCompleted());
        todoEntity.setCreatedBy(createdBy);
        todoEntity.setLastModifiedBy(createdBy);

        TodoEntity savedTodoEntity = todoRepository.save(todoEntity);

        return modelMapper.map(savedTodoEntity, TodoDto.class);
    }
}
