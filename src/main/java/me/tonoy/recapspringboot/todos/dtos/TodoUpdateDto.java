package me.tonoy.recapspringboot.todos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateDto extends TodoCreateDto {
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String updatedBy;
}
