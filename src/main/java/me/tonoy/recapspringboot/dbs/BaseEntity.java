package me.tonoy.recapspringboot.dbs;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import me.tonoy.recapspringboot.utils.DateService;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "created_date", updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String lastModifiedBy;

    @Column(name = "modified_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime modifiedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;

    @PrePersist
    private void setCreationDateTime() {
        this.createdDate = DateService.getCurrentZonedDateTime();
        this.modifiedDate = DateService.getCurrentZonedDateTime();
    }

    @PreUpdate
    private void setModifyDateTime() {
        this.modifiedDate = DateService.getCurrentZonedDateTime();
    }

}
