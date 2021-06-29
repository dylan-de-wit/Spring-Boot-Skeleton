package com.dylandewit.skeleton.api.base.dto;

import com.dylandewit.skeleton.api.base.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseViewDto<T extends BaseModel> {
    private Long id;
    private String createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public BaseViewDto(T t) {
        if (t == null) throw new IllegalArgumentException("First parameter in BaseViewDto may not be null");

        this.id = t.getId();
        this.createdBy = t.getCreatedBy();
        this.createdOn = t.getCreatedOn();
        this.updatedOn = t.getUpdatedOn();
    }
}
