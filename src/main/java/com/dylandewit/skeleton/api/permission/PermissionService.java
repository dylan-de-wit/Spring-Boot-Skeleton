package com.dylandewit.skeleton.api.permission;

import com.dylandewit.skeleton.api.base.BaseService;
import com.dylandewit.skeleton.api.permission.dto.CreatePermissionDto;
import com.dylandewit.skeleton.api.permission.dto.ViewPermissionDto;
import com.dylandewit.skeleton.api.permission.models.Permission;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService extends BaseService<Permission, ViewPermissionDto, CreatePermissionDto> {
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        super(permissionRepository);

        this.permissionRepository = permissionRepository;
    }

    @Override
    protected ViewPermissionDto mapToDto(Permission permission, List<String> includes) {
        return new ViewPermissionDto(permission, includes);
    }

    @Override
    protected Permission mapForUpdate(Permission permission, CreatePermissionDto createPermissionDto) {
        throw new NotImplementedException();
    }
}
