package me.tonoy.recapspringboot.todos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TodoDto {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private String createdBy;
    private String modifiedBy;
}
