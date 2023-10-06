package me.tonoy.recapspringboot.todos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateUpdateDto {
    private String title;
    private String description;
    private boolean completed;
}
