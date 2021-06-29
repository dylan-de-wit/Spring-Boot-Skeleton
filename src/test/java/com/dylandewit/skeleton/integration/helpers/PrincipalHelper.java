package com.dylandewit.skeleton.integration.helpers;

import com.dylandewit.skeleton.api.permission.PermissionRepository;
import com.dylandewit.skeleton.api.permission.models.Permission;
import com.dylandewit.skeleton.api.permission.models.Permissions;
import com.dylandewit.skeleton.api.role.RoleRepository;
import com.dylandewit.skeleton.api.role.models.Role;
import com.dylandewit.skeleton.api.user.UserRepository;
import com.dylandewit.skeleton.api.user.models.User;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PrincipalHelper {
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public PrincipalHelper(PermissionRepository permissionRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public Principal mockPrincipal(String username, List<Permissions> permissions) {
        Set<Permission> permissionSet = permissionRepository.findAllByNameIn(permissions);
        Role role = /*roleRepository.save(RoleData.buildRole(permissionSet))*/ new Role();
        Optional<User> oUser = userRepository.findFirstByUsername(username);
        oUser.orElseGet(() -> userRepository.save(User.builder().email(username).firstName("test").lastName("test").role(role).build()));
        Principal principalMock = Mockito.mock(Principal.class);
        Mockito.when(principalMock.getName()).thenReturn("test");

        return principalMock;
    }
}
