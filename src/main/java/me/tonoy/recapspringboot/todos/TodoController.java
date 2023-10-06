package me.tonoy.recapspringboot.todos;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

@Slf4j
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
    ResponseEntity<TodoDto> createTodo(@RequestBody TodoCreateUpdateDto todo, UriComponentsBuilder ucb, Principal principal) {
        TodoDto todoDto = todoService.createTodo(todo, principal.getName());
        URI location = ucb.path("/api/todos/{todoId}").buildAndExpand(todoDto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
