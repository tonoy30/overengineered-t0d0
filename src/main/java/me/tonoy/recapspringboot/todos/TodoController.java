package me.tonoy.recapspringboot.todos;

import lombok.RequiredArgsConstructor;
import me.tonoy.recapspringboot.todos.dtos.TodoCreateDto;
import me.tonoy.recapspringboot.todos.dtos.TodoDto;
import me.tonoy.recapspringboot.todos.dtos.TodoUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{todoId}")
    ResponseEntity<TodoDto> getTodoById(@PathVariable String todoId, Principal principal) {
        Optional<TodoDto> todoDto = todoService.getTodoByIdAndCreateBy(todoId, principal.getName());
        return todoDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<TodoDto> createTodo(@RequestBody TodoCreateDto todo,
                                       UriComponentsBuilder ucb,
                                       Principal principal) {
        todo.setCreatedBy(principal.getName());
        TodoDto todoDto = todoService.createTodo(todo);
        URI location = ucb.path("/api/todos/{todoId}").buildAndExpand(todoDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{todoId}")
    ResponseEntity<Void> updateTodo(@PathVariable String todoId,
                                    @RequestBody TodoUpdateDto todo,
                                    Principal principal) {
        todo.setUpdatedBy(principal.getName());
        Optional<TodoDto> todoDto = todoService.updateTodo(todo);
        return todoDto.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
