package com.dylandewit.skeleton.resources.role.dto;

import com.dylandewit.skeleton.helpers.IncludesHelper;
import com.dylandewit.skeleton.resources.base.dto.BaseViewDto;
import com.dylandewit.skeleton.resources.permission.dto.ViewPermissionDto;
import com.dylandewit.skeleton.resources.role.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

    public ViewRoleDto(Role role, List<String> includes) {
        super(role, includes);
        List<String> finalIncludes = IncludesHelper.fetchNestedIncludes(includes, "role");

        this.name = role.getName();
        this.description = role.getDescription();

        if (IncludesHelper.hasInclude(finalIncludes, "permission"))
            this.permissions = role.getPermissions().stream()
                    .map(p -> new ViewPermissionDto(p, finalIncludes))
                    .collect(Collectors.toSet());

    }
}
