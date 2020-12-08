package com.dylandewit.skeleton.resources.base.dto;

import lombok.Data;

@Data
public abstract class BaseCreateDto<T> {

    public abstract T toModel();
}
