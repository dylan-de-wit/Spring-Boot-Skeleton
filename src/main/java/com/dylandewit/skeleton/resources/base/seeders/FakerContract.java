package com.dylandewit.skeleton.resources.base.seeders;

import com.dylandewit.skeleton.resources.base.models.BaseModel;

public interface FakerContract<T extends BaseModel> {
    T make();
}
