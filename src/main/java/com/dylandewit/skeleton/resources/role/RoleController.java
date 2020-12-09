package com.dylandewit.skeleton.resources.role;

import com.dylandewit.skeleton.resources.base.BaseController;
import com.dylandewit.skeleton.resources.role.dto.CreateRoleDto;
import com.dylandewit.skeleton.resources.role.dto.ViewRoleDto;
import com.dylandewit.skeleton.resources.role.models.Role;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Roles", description = "All role related endpoints")
@RequestMapping(value = "/roles")
public class RoleController extends BaseController<Role, ViewRoleDto, CreateRoleDto> {

    @Autowired
    public RoleController(RoleService roleService) {
        super(roleService);
    }
}
