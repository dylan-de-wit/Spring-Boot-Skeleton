package com.dylandewit.skeleton.resources.user;

import com.dylandewit.skeleton.resources.BaseRepository;
import com.dylandewit.skeleton.resources.user.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findFirstByUsername(String username);

    boolean existsByEmail(String email);
}
