package com.dylandewit.skeleton.api.user.dto;

import com.dylandewit.skeleton.api.base.dto.BaseGetDto;
import com.dylandewit.skeleton.api.role.dto.RoleGetDto;
import com.dylandewit.skeleton.api.user.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetDto extends BaseGetDto<User> {

    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private RoleGetDto role;

    public UserGetDto(User user) {
        super(user);
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = new RoleGetDto(user.getRole());
    }
}
