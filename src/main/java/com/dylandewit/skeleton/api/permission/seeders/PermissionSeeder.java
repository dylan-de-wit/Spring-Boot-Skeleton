package com.dylandewit.skeleton.api.permission.seeders;

import com.dylandewit.skeleton.api.base.seeders.Seeder;
import com.dylandewit.skeleton.api.permission.PermissionRepository;
import com.dylandewit.skeleton.api.permission.models.Permission;
import com.dylandewit.skeleton.api.permission.models.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class PermissionSeeder implements Seeder {
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionSeeder(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void seed() {
        List<Permissions> permissions = Arrays.asList(Permissions.values());
        List<Permission> toSave = new ArrayList<>();
        permissions.stream()
                .filter(p -> !permissionRepository.existsByName(p))
                .forEach(p -> toSave.add(new Permission(p, new HashSet<>())));

        permissionRepository.saveAll(toSave);
    }
}
