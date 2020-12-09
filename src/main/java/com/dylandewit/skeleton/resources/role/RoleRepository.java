package com.dylandewit.skeleton.resources.role;

import com.dylandewit.skeleton.resources.base.BaseRepository;
import com.dylandewit.skeleton.resources.role.models.Role;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
