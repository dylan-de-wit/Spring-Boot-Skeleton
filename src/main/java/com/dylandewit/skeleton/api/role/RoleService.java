package com.dylandewit.skeleton.api.role;

import com.dylandewit.skeleton.api.base.BaseService;
import com.dylandewit.skeleton.api.role.dto.RoleGetDto;
import com.dylandewit.skeleton.api.role.dto.RolePostDto;
import com.dylandewit.skeleton.api.role.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, RoleGetDto, RolePostDto> {

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
    }

    @Override
    protected RoleGetDto mapToDto(Role role) {
        return new RoleGetDto(role);
    }

    @Override
    protected Role mapForUpdate(Role role, RolePostDto createRoleDto) {
        return role;
    }
}
