package com.dylandewit.skeleton.integration.helpers;

import com.dylandewit.skeleton.api.permission.models.Permissions;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

public class PermissionMockHelper {

    public static void setupMockUser(UserDetailsService userDetailsService, List<SimpleGrantedAuthority> authorities) {
        setupMockUser(userDetailsService, authorities, "test");
    }

    public static void setupMockUser(UserDetailsService userDetailsService, List<SimpleGrantedAuthority> authorities, String username) {
        Mockito.when(userDetailsService.loadUserByUsername(any()))
                .thenReturn(new org.springframework.security.core.userdetails.User(
                                username,
                                "test",
                                true,
                                true,
                                true,
                                true,
                                authorities
                        )
                );
    }

    public static void setupMockUser(UserDetailsService userDetailsService, String username, List<Permissions> permissions) {
        List<SimpleGrantedAuthority> authorities = permissions.stream().map(p -> new SimpleGrantedAuthority(p.name())).collect(Collectors.toList());

        setupMockUser(userDetailsService, authorities, username);
    }
}
