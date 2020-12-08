package com.dylandewit.skeleton.resources.user;

import com.dylandewit.skeleton.resources.BaseController;
import com.dylandewit.skeleton.resources.user.dto.CreateUserDto;
import com.dylandewit.skeleton.resources.user.dto.ViewUserDto;
import com.dylandewit.skeleton.resources.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController<User, ViewUserDto, CreateUserDto> {

    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }

    @GetMapping("/me")
    public ViewUserDto findMe(/*@CurrentUser User user*/  @RequestParam(name = "includes", required = false) List<String> includes) {
        return new ViewUserDto(null, includes);
    }
}
