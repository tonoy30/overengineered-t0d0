package me.tonoy.recapspringboot.todos;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import me.tonoy.recapspringboot.dbs.BaseEntity;
import me.tonoy.recapspringboot.dbs.RandomIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Data
@Slf4j
@Getter
@Setter
@Entity
@Table(name = "todos")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TodoEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = RandomIdGenerator.ID_GENERATOR_NAME)
    @GenericGenerator(
            type = RandomIdGenerator.class,
            name = RandomIdGenerator.ID_GENERATOR_NAME,
            parameters = {
                    @Parameter(name = RandomIdGenerator.ID_PREFIX_PARAMETER, value = "todo"),
                    @Parameter(name = RandomIdGenerator.ID_LENGTH_PARAMETER, value = "10")
            }
    )
    @Basic(optional = false)
    @Column(name = "id", updatable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_completed")
    private boolean isCompleted;
}

