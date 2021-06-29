package com.dylandewit.skeleton.api.permission;

import com.dylandewit.skeleton.api.base.BaseService;
import com.dylandewit.skeleton.api.permission.dto.PermissionGetDto;
import com.dylandewit.skeleton.api.permission.dto.PermissionPostDto;
import com.dylandewit.skeleton.api.permission.models.Permission;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends BaseService<Permission, PermissionGetDto, PermissionPostDto> {

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        super(permissionRepository);
    }

    @Override
    protected PermissionGetDto mapToDto(Permission permission) {
        return new PermissionGetDto(permission);
    }

    @Override
    protected Permission mapForUpdate(Permission permission, PermissionPostDto createPermissionDto) {
        throw new NotImplementedException();
    }
}
