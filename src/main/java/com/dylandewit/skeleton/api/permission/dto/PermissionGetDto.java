package com.dylandewit.skeleton.api.permission.dto;

import com.dylandewit.skeleton.api.base.dto.BaseGetDto;
import com.dylandewit.skeleton.api.permission.models.Permission;
import com.dylandewit.skeleton.api.permission.models.Permissions;
import com.dylandewit.skeleton.api.role.dto.RoleGetDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class PermissionGetDto extends BaseGetDto<Permission> {

    private Permissions name;
    private String category;

    @JsonIgnoreProperties({"permissions"})
    private Set<RoleGetDto> roles;

    public PermissionGetDto(Permission permission) {
        super(permission);

        this.name = permission.getName();
        this.category = name.getCategory();
        this.roles = permission.getRoles().stream()
                .map(RoleGetDto::new)
                .collect(Collectors.toSet());
    }
}
