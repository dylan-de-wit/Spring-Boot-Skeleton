package com.dylandewit.skeleton.api.base.dto;

import lombok.Data;

@Data
public abstract class BaseCreateDto<T> {

    public abstract T toModel();
}
