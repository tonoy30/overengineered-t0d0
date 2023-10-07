package me.tonoy.recapspringboot.todos;

import me.tonoy.recapspringboot.RecapSpringBootApplication;
import me.tonoy.recapspringboot.config.UserConfiguration;
import me.tonoy.recapspringboot.todos.dtos.TodoCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
        RecapSpringBootApplication.class,
})
@ActiveProfiles("test")
class TodoControllerTest {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    UserConfiguration userConfiguration;

    @Test
    @DirtiesContext
    void shouldCreateTodo() {
        TodoCreateDto todo = new TodoCreateDto();
        todo.setTitle("Post about this project in linkedin!");
        todo.setDescription("");
        todo.setCompleted(false);

        ResponseEntity<String> createResponse = restTemplate
                .withBasicAuth(userConfiguration.getName(), userConfiguration.getPassword())
                .postForEntity("/api/todos", todo, String.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI location = createResponse.getHeaders().getLocation();
        ResponseEntity<String> response = restTemplate
                .withBasicAuth(userConfiguration.getName(), userConfiguration.getPassword())
                .getForEntity(location, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}