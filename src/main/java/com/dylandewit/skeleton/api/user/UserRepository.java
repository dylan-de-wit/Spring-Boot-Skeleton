package com.dylandewit.skeleton.api.user;

import com.dylandewit.skeleton.api.base.BaseRepository;
import com.dylandewit.skeleton.api.user.models.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findFirstByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByUsernameOrEmail(String email, String username);
}
