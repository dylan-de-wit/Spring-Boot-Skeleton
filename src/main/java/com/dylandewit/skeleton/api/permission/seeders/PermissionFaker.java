package com.dylandewit.skeleton.api.permission.seeders;

import com.dylandewit.skeleton.api.base.seeders.FakerContract;
import com.dylandewit.skeleton.api.permission.models.Permission;
import com.dylandewit.skeleton.api.permission.models.Permissions;
import com.dylandewit.skeleton.api.role.seeders.RoleFaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PermissionFaker implements FakerContract<Permission> {
    private final RoleFaker roleFaker;

    @Autowired
    public PermissionFaker(RoleFaker roleFaker) {
        this.roleFaker = roleFaker;
    }

    public Permission make() {
        Permission permission = new Permission(Permissions.VIEW_USERS, new HashSet<>());
        roleFaker.makeMultiple().forEach(r -> {
            r.getPermissions().add(permission);
            permission.setRoles(new HashSet<>(Set.of(r)));
        });

        return permission;
    }

    @Override
    public Set<Permission> makeMultiple() {
        return new HashSet<>(Set.of(make(), make(), make()));
    }
}
