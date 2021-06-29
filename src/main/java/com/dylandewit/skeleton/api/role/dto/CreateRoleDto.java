package com.dylandewit.skeleton.api.role.dto;

import com.dylandewit.skeleton.api.base.dto.BaseCreateDto;
import com.dylandewit.skeleton.api.role.models.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateRoleDto extends BaseCreateDto<Role> {

    @NotEmpty
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @Override
    public Role toModel() {
        return Role.builder()
                .name(name)
                .description(description)
                .build();
    }
}
