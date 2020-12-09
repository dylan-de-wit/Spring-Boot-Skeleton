package com.dylandewit.skeleton.resources.permission;

import com.dylandewit.skeleton.resources.base.BaseController;
import com.dylandewit.skeleton.resources.base.models.OkResponse;
import com.dylandewit.skeleton.resources.permission.dto.CreatePermissionDto;
import com.dylandewit.skeleton.resources.permission.dto.ViewPermissionDto;
import com.dylandewit.skeleton.resources.permission.models.Permission;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Permissions", description = "All permission related endpoints")
@RequestMapping(value = "/permissions")
public class PermissionController extends BaseController<Permission, ViewPermissionDto, CreatePermissionDto> {

    @Autowired
    public PermissionController(PermissionService permissionService) {
        super(permissionService);
    }

    @Override
    @ApiIgnore
    public ViewPermissionDto update(Long id, @Valid CreatePermissionDto body, List<String> includes) {
        throw new NotImplementedException();
    }

    @Override
    @ApiIgnore
    public ViewPermissionDto create(@Valid CreatePermissionDto body, List<String> includes) {
        throw new NotImplementedException();
    }

    @Override
    @ApiIgnore
    public OkResponse delete(Long id) {
        throw new NotImplementedException();
    }
}
