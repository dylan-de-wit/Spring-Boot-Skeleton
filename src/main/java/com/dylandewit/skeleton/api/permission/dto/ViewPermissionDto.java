package com.dylandewit.skeleton.api.permission.dto;

import com.dylandewit.skeleton.api.base.dto.BaseViewDto;
import com.dylandewit.skeleton.api.permission.models.Permission;
import com.dylandewit.skeleton.api.permission.models.Permissions;
import com.dylandewit.skeleton.api.role.dto.ViewRoleDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ViewPermissionDto extends BaseViewDto<Permission> {

    private Permissions name;
    private String category;

    @JsonIgnoreProperties({"permissions"})
    private Set<ViewRoleDto> roles;

    public ViewPermissionDto(Permission permission) {
        super(permission);

        this.name = permission.getName();
        this.category = name.getCategory();
        this.roles = permission.getRoles().stream()
                .map(ViewRoleDto::new)
                .collect(Collectors.toSet());
    }
}
