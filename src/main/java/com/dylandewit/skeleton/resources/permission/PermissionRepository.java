package com.dylandewit.skeleton.resources.permission;

import com.dylandewit.skeleton.resources.base.BaseRepository;
import com.dylandewit.skeleton.resources.permission.models.Permission;
import com.dylandewit.skeleton.resources.permission.models.Permissions;

import java.util.List;
import java.util.Set;

public interface PermissionRepository extends BaseRepository<Permission> {

    boolean existsByName(Permissions name);

    Set<Permission> findAllByNameIn(List<Permissions> names);
}
