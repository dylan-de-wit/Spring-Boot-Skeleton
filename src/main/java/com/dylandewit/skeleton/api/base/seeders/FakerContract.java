package com.dylandewit.skeleton.api.base.seeders;

import com.dylandewit.skeleton.api.base.models.BaseModel;

public interface FakerContract<T extends BaseModel> {
    T make();
}
