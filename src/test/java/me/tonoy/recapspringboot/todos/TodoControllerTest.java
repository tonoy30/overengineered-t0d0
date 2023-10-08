package me.tonoy.recapspringboot.todos;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import me.tonoy.recapspringboot.RecapSpringBootApplication;
import me.tonoy.recapspringboot.config.UserConfiguration;
import me.tonoy.recapspringboot.todos.dtos.TodoCreateDto;
import me.tonoy.recapspringboot.todos.dtos.TodoDto;
import me.tonoy.recapspringboot.todos.dtos.TodoUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {
                RecapSpringBootApplication.class,
        })
@ActiveProfiles("test")
class TodoControllerTest {
    final TodoDto todoDto = new TodoDto();
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    UserConfiguration userConfiguration;
    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        todoDto.setTitle("Post about this project in linkedin!");
        todoDto.setDescription("laboriosam mollitia et enim quasi adipisci quia provident illum," + " laboriosam mollitia et enim quasi adipisci quia provident illum");
        todoDto.setCompleted(false);
        todoDto.setCreatedBy(userConfiguration.getName());
        todoDto.setModifiedBy(userConfiguration.getName());

        restTemplate = restTemplate.withBasicAuth(userConfiguration.getName(), userConfiguration.getPassword());
    }

    @Test
    @DirtiesContext
    void shouldCreateTodo() {
        TodoCreateDto todo = modelMapper.map(todoDto, TodoCreateDto.class);
        ResponseEntity<String> createResponse = restTemplate.postForEntity("/api/todos", todo, String.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI location = createResponse.getHeaders().getLocation();
        ResponseEntity<String> response = restTemplate.getForEntity(location, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        String title = documentContext.read("$.title");
        assertThat(title).isEqualTo(todoDto.getTitle());
    }

    @Test
    @DirtiesContext
    void shouldUpdateTodo() {
        todoDto.setId("todo_DatamsJudmOFUN2C");
        TodoUpdateDto todoUpdateDto = modelMapper.map(todoDto, TodoUpdateDto.class);
        todoUpdateDto.setCompleted(true);

        HttpEntity<TodoUpdateDto> request = new HttpEntity<>(todoUpdateDto);
        ResponseEntity<Void> response = restTemplate.exchange(String.format("/api/todos/%s", todoDto.getId()), HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);


        ResponseEntity<String> getResponse = restTemplate.getForEntity(String.format("/api/todos/%s", todoDto.getId()), String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());

        boolean completed = documentContext.read("$.completed");
        assertThat(completed).isEqualTo(todoUpdateDto.isCompleted());
    }
}