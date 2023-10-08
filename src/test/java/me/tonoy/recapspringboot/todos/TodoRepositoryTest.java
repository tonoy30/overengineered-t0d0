package me.tonoy.recapspringboot.todos;

import me.tonoy.recapspringboot.todos.dtos.TodoDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoRepositoryTest {
    static final TodoDto todo = new TodoDto();
    @Autowired
    TodoRepository todoRepository;

    @BeforeAll
    static void setup() {
        todo.setId("todo_abcdef0123456719");
        todo.setCreatedBy("mary.jane@example.com");
    }

    @Test
    void shouldFindByIdAndCreatedBy() {
        Optional<TodoEntity> mayBeTodo = todoRepository.findByIdAndCreatedBy(todo.getId(), todo.getCreatedBy());
        assertThat(mayBeTodo.isPresent()).isTrue();
    }

    @Test
    void shouldFindByIdAndCreatedByDoesNotHaveAnyTodoIfCreatedBy() {
        Optional<TodoEntity> mayBeTodo = todoRepository.findByIdAndCreatedBy(todo.getId(), "test@gmail.com");
        assertThat(mayBeTodo.isEmpty()).isTrue();
    }

    @Test
    void shouldExistsByIdAndCreatedBy() {
        boolean isExist = todoRepository.existsByIdAndCreatedBy(todo.getId(), todo.getCreatedBy());
        assertThat(isExist).isTrue();
    }

    @Test
    void shouldExistsByIdAndCreatedByBeFalseIfTodoDoesNotCreatedByGivenUser() {
        boolean isExist = todoRepository.existsByIdAndCreatedBy(todo.getId(), "test@gmail.com");
        assertThat(isExist).isFalse();
    }
}