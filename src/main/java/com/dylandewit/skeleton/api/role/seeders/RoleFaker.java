package com.dylandewit.skeleton.api.role.seeders;

import com.dylandewit.skeleton.api.base.seeders.FakerContract;
import com.dylandewit.skeleton.api.permission.seeders.PermissionFaker;
import com.dylandewit.skeleton.api.role.models.Role;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleFaker implements FakerContract<Role> {
    private final PermissionFaker permissionFaker;

    @Autowired
    public RoleFaker(PermissionFaker permissionFaker) {
        this.permissionFaker = permissionFaker;
    }

    public Role make() {
        Faker faker = new Faker();

        Role role = new Role(faker.job().title(), faker.job().field(), new HashSet<>());
        permissionFaker.makeMultiple().forEach(p -> {
            p.setRoles(new HashSet<>(Set.of(role)));
            role.getPermissions().add(p);
        });

        return role;
    }

    @Override
    public Set<Role> makeMultiple() {
        return new HashSet<>(Set.of(make(), make(), make()));
    }
}
