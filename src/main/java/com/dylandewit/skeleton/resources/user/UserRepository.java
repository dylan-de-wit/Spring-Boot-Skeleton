package com.dylandewit.skeleton.resources.user;

import com.dylandewit.skeleton.resources.BaseRepository;
import com.dylandewit.skeleton.resources.user.model.User;

public interface UserRepository extends BaseRepository<User> {
    User findFirstByUsername(String username);
}
