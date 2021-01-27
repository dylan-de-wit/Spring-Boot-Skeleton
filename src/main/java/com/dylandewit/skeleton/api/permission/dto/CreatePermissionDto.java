package com.dylandewit.skeleton.api.permission.dto;

import com.dylandewit.skeleton.api.base.dto.BaseCreateDto;
import com.dylandewit.skeleton.api.permission.models.Permission;
import com.dylandewit.skeleton.api.permission.models.Permissions;
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
