package com.dylandewit.skeleton.resources;

import java.time.LocalDateTime;

public interface BaseModel {

    Long getId();

    LocalDateTime getCreatedOn();

    LocalDateTime getUpdatedOn();
}
