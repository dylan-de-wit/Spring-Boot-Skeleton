package com.dylandewit.skeleton.resources.user;

import com.dylandewit.skeleton.resources.BaseController;
import com.dylandewit.skeleton.resources.user.dto.CreateUserDto;
import com.dylandewit.skeleton.resources.user.dto.ViewUserDto;
import com.dylandewit.skeleton.resources.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController<User, ViewUserDto, CreateUserDto> {

    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }
}
