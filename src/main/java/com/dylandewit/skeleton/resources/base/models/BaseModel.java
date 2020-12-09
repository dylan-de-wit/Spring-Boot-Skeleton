package com.dylandewit.skeleton.resources.base.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseModel implements Serializable, Comparable<BaseModel> {
    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Setter
    protected Boolean active = true;

    @CreatedBy
    protected String createdBy;

    @CreatedDate
    protected LocalDateTime createdOn;

    @LastModifiedDate
    protected LocalDateTime updatedOn;

    @Override
    public int compareTo(BaseModel o) {
        if (o == null) throw new IllegalArgumentException("Object should not be null while comparing");
        if (o.id == null) throw new IllegalArgumentException("Id should not be null while comparing");

        return o.id.compareTo(id);
    }
}
