package com.dylandewit.skeleton.resources.role.dto;

import com.dylandewit.skeleton.resources.base.dto.BaseCreateDto;
import com.dylandewit.skeleton.resources.role.models.Role;
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
