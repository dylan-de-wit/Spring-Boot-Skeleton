package com.dylandewit.skeleton.api.role;

import com.dylandewit.skeleton.api.base.BaseController;
import com.dylandewit.skeleton.api.role.dto.RoleGetDto;
import com.dylandewit.skeleton.api.role.dto.RolePostDto;
import com.dylandewit.skeleton.api.role.models.Role;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Roles", description = "All role related endpoints")
@RequestMapping(value = "/roles")
public class RoleController extends BaseController<Role, RoleGetDto, RolePostDto> {

    @Autowired
    public RoleController(RoleService roleService) {
        super(roleService);
    }
}
