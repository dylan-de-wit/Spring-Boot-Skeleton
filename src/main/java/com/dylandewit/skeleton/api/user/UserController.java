package com.dylandewit.skeleton.api.user;

import com.dylandewit.skeleton.api.base.BaseController;
import com.dylandewit.skeleton.api.user.dto.UserGetDto;
import com.dylandewit.skeleton.api.user.dto.UserPostDto;
import com.dylandewit.skeleton.api.user.models.User;
import com.dylandewit.skeleton.exception.response.ApiError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Users", description = "All user related endpoints")
@RequestMapping(value = "/users")
public class UserController extends BaseController<User, UserGetDto, UserPostDto> {

    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }

    @ApiResponse(code = 404, message = "Not found", response = ApiError.class)
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserGetDto findMe(/*@CurrentUser User user*/) {
        return new UserGetDto(null);
    }
}
