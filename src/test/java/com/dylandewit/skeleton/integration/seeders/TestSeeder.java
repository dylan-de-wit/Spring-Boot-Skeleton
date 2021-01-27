package com.dylandewit.skeleton.integration.seeders;

import com.dylandewit.skeleton.api.base.models.BaseModel;

import java.util.List;

public interface TestSeeder<T extends BaseModel> {
    T seedOne();

    List<T> seedMultiple();
}
