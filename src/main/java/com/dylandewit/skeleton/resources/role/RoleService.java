package com.dylandewit.skeleton.resources.role;

import com.dylandewit.skeleton.resources.base.BaseService;
import com.dylandewit.skeleton.resources.role.dto.CreateRoleDto;
import com.dylandewit.skeleton.resources.role.dto.ViewRoleDto;
import com.dylandewit.skeleton.resources.role.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService<Role, ViewRoleDto, CreateRoleDto> {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);

        this.roleRepository = roleRepository;
    }

    @Override
    protected ViewRoleDto mapToDto(Role role, List<String> includes) {
        return new ViewRoleDto(role, includes);
    }

    @Override
    protected Role mapForUpdate(Role role, CreateRoleDto createRoleDto) {
        return role;
    }
}
