package com.dylandewit.skeleton.resources.permission.dto;

import com.dylandewit.skeleton.resources.base.dto.BaseCreateDto;
import com.dylandewit.skeleton.resources.permission.models.Permission;
import com.dylandewit.skeleton.resources.permission.models.Permissions;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreatePermissionDto extends BaseCreateDto<Permission> {

    @NotEmpty
    @Size(max = 255)
    private Permissions name;

    @Override
    public Permission toModel() {
        return Permission.builder()
                .name(name)
                .build();
    }
}
