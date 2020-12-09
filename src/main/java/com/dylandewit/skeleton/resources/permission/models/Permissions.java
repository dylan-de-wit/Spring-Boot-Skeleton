package com.dylandewit.skeleton.resources.permission.models;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Permissions {
    VIEW_USERS("USERS", true),
    CREATE_USERS("USERS", true),
    EDIT_USERS("USERS", true),
    DELETE_USERS("USERS", true),

    VIEW_ROLES("ROLES", true),
    CREATE_ROLES("ROLES", true),
    EDIT_ROLES("ROLES", true),
    DELETE_ROLES("ROLES", true),

    VIEW_PERMISSIONS("PERMISSIONS", true);

    @Getter
    private final String category;

    @Getter
    private final boolean adminOnly;

    Permissions(String category, boolean adminOnly) {
        this.category = category;
        this.adminOnly = adminOnly;
    }

    Permissions(String category) {
        this(category, false);
    }

    public static List<Permissions> forCategory(String category) {
        return Arrays.stream(Permissions.values())
                .filter(p -> p.category.equals(category.toUpperCase()))
                .collect(Collectors.toList());
    }

    public static List<Permissions> nonAdmin() {
        return Arrays.stream(Permissions.values())
                .filter(p -> !p.isAdminOnly())
                .collect(Collectors.toList());
    }
}
