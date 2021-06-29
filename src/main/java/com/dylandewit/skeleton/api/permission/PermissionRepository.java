package com.dylandewit.skeleton.api.permission;

import com.dylandewit.skeleton.api.base.BaseRepository;
import com.dylandewit.skeleton.api.permission.models.Permission;
import com.dylandewit.skeleton.api.permission.models.Permissions;

import java.util.List;
import java.util.Set;

public interface PermissionRepository extends BaseRepository<Permission> {

    boolean existsByName(Permissions name);

    Set<Permission> findAllByNameIn(List<Permissions> names);
}
