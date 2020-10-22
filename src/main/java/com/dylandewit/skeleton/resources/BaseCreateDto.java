package com.dylandewit.skeleton.resources;

import lombok.Data;

@Data
public abstract class BaseCreateDto<T> {

    public abstract T toModel();
}
