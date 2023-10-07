package me.tonoy.recapspringboot.todos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateDto {
    private String title;
    private String description;
    private boolean completed;
    private String createdBy;
    @JsonIgnore
    private String modifiedBy;
}
