package com.dylandewit.skeleton.resources.user;

import com.dylandewit.skeleton.resources.base.BaseRepository;
import com.dylandewit.skeleton.resources.user.models.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findFirstByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByUsernameOrEmail(String email, String username);
}
