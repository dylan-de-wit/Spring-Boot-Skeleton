package com.dylandewit.skeleton.api.role.dto;

import com.dylandewit.skeleton.api.base.dto.BaseGetDto;
import com.dylandewit.skeleton.api.permission.dto.PermissionGetDto;
import com.dylandewit.skeleton.api.role.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class RoleGetDto extends BaseGetDto<Role> {

    private String name;
    private String description;

    @JsonIgnoreProperties({"roles"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<PermissionGetDto> permissions;

    public RoleGetDto(Role role) {
        super(role);

        this.name = role.getName();
        this.description = role.getDescription();

        this.permissions = role.getPermissions().stream()
                .map(PermissionGetDto::new)
                .collect(Collectors.toSet());

    }
}
