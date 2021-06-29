package com.dylandewit.skeleton.api.base.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseModel implements Serializable, Comparable<BaseModel> {
    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "active", nullable = false, columnDefinition = "BIT default 1")
    protected Boolean active = true;

    @CreatedBy
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_on", nullable = false, columnDefinition = "datetime2 default CURRENT_TIMESTAMP")
    protected LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "updated_on", nullable = false, columnDefinition = "datetime2 default CURRENT_TIMESTAMP")
    protected LocalDateTime updatedOn;

    @Override
    public int compareTo(BaseModel o) {
        if (o == null) throw new IllegalArgumentException("Object should not be null when comparing");
        if (o.id == null) throw new IllegalArgumentException("Id should not be null when comparing");

        return o.id.compareTo(id);
    }
}