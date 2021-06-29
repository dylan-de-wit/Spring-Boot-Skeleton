package com.dylandewit.skeleton.api.user.dto;

import com.dylandewit.skeleton.api.base.dto.BaseViewDto;
import com.dylandewit.skeleton.api.role.dto.ViewRoleDto;
import com.dylandewit.skeleton.api.user.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewUserDto extends BaseViewDto<User> {

    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private ViewRoleDto role;

    public ViewUserDto(User user) {
        super(user);
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = new ViewRoleDto(user.getRole());
    }
}
