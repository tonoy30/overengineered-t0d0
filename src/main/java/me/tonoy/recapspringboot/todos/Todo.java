package me.tonoy.recapspringboot.todos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.tonoy.recapspringboot.dbs.RandomIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Slf4j
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(generator = RandomIdGenerator.GENERATOR_NAME)
    @GenericGenerator(
            type = RandomIdGenerator.class,
            name = RandomIdGenerator.ID_GENERATOR_NAME,
            parameters = {
                    @Parameter(name = RandomIdGenerator.ID_PREFIX_PARAMETER, value = "todo"),
                    @Parameter(name = RandomIdGenerator.ID_LENGTH_PARAMETER, value = "10")
            }
    )
    private String id;
}
