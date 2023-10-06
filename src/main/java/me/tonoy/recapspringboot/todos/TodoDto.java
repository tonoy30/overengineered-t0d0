package me.tonoy.recapspringboot.todos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoDto {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private String createdBy;
    private String modifiedBy;
}
