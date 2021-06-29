package com.dylandewit.skeleton.api.role.seeders;

import com.dylandewit.skeleton.api.base.seeders.Seeder;
import com.dylandewit.skeleton.api.permission.PermissionRepository;
import com.dylandewit.skeleton.api.permission.models.Permissions;
import com.dylandewit.skeleton.api.role.RoleRepository;
import com.dylandewit.skeleton.api.role.models.Role;
import com.dylandewit.skeleton.exception.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class RoleSeeder implements Seeder {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public RoleSeeder(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void seed() {
        saveDefaultRoles();
        saveDefaultRolePermissions();
    }

    private void saveDefaultRoles() {
        List<Role> roles = List.of(
                new Role("Admin", "Admin role", new HashSet<>()),
                new Role("User", "User role", new HashSet<>())
        );

        List<Role> toSave = new ArrayList<>();
        roles.stream()
                .filter(r -> !roleRepository.existsByName(r.getName()))
                .forEach(toSave::add);

        if (!toSave.isEmpty())
            roleRepository.saveAll(toSave);
    }

    private void saveDefaultRolePermissions() {
        Role admin = roleRepository.findByName("Admin").orElseThrow(() -> new NotFoundException("Admin role"));
        permissionRepository.findAll().iterator().forEachRemaining(p -> admin.getPermissions().add(p));

        Role user = roleRepository.findByName("User").orElseThrow(() -> new NotFoundException("User role"));
        user.getPermissions().addAll(permissionRepository.findAllByNameIn(Permissions.nonAdmin()));

        roleRepository.saveAll(List.of(admin, user));
    }
}
