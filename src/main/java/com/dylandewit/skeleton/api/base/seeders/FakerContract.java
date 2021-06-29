package com.dylandewit.skeleton.api.base.seeders;

import com.dylandewit.skeleton.api.base.models.BaseModel;

import java.util.Set;

public interface FakerContract<T extends BaseModel> {
    T make();

    Set<T> makeMultiple();
}
