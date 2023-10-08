package me.tonoy.recapspringboot.todos;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import me.tonoy.recapspringboot.dbs.BaseEntity;
import me.tonoy.recapspringboot.dbs.RandomIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Data
@Slf4j
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "todos")
@Where(clause = "is_deleted=false")
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE todos SET is_deleted=true WHERE id=?")
public class TodoEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = RandomIdGenerator.ID_GENERATOR_NAME)
    @GenericGenerator(
            type = RandomIdGenerator.class,
            name = RandomIdGenerator.ID_GENERATOR_NAME,
            parameters = {
                    @Parameter(name = RandomIdGenerator.ID_PREFIX_PARAMETER, value = "todo")
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
    private boolean isCompleted = Boolean.FALSE;
}

