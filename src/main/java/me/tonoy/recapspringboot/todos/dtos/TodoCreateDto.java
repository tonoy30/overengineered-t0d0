package me.tonoy.recapspringboot.todos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateDto {
    private String title;
    private String description;
    private boolean completed;
    @JsonIgnore
    private String createdBy;
}