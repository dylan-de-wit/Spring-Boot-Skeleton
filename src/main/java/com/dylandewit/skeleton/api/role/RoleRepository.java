package com.dylandewit.skeleton.api.role;

import com.dylandewit.skeleton.api.base.BaseRepository;
import com.dylandewit.skeleton.api.role.models.Role;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
