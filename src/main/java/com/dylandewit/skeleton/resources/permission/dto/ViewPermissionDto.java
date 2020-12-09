package com.dylandewit.skeleton.resources.permission.dto;

import com.dylandewit.skeleton.helpers.IncludesHelper;
import com.dylandewit.skeleton.resources.base.dto.BaseViewDto;
import com.dylandewit.skeleton.resources.permission.models.Permission;
import com.dylandewit.skeleton.resources.permission.models.Permissions;
import com.dylandewit.skeleton.resources.role.dto.ViewRoleDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ViewPermissionDto extends BaseViewDto<Permission> {

    private Permissions name;
    private String category;

    @JsonIgnoreProperties({"permissions"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ViewRoleDto> roles;

    public ViewPermissionDto(Permission permission, List<String> includes) {
        super(permission, includes);
        List<String> finalIncludes = IncludesHelper.fetchNestedIncludes(includes, "permission");

        this.name = permission.getName();
        this.category = name.getCategory();

        if (IncludesHelper.hasInclude(finalIncludes, "role"))
            this.roles = permission.getRoles().stream()
                    .map(r -> new ViewRoleDto(r, finalIncludes))
                    .collect(Collectors.toSet());
    }
}
