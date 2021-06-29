package com.dylandewit.skeleton.api.role.dto;

import com.dylandewit.skeleton.api.base.dto.BaseViewDto;
import com.dylandewit.skeleton.api.permission.dto.ViewPermissionDto;
import com.dylandewit.skeleton.api.role.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ViewRoleDto extends BaseViewDto<Role> {

    private String name;
    private String description;

    @JsonIgnoreProperties({"roles"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ViewPermissionDto> permissions;

    public ViewRoleDto(Role role) {
        super(role);

        this.name = role.getName();
        this.description = role.getDescription();

        this.permissions = role.getPermissions().stream()
                .map(ViewPermissionDto::new)
                .collect(Collectors.toSet());

    }
}
