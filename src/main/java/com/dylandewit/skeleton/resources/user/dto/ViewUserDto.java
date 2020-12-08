package com.dylandewit.skeleton.resources.user.dto;

import com.dylandewit.skeleton.resources.BaseViewDto;
import com.dylandewit.skeleton.resources.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ViewUserDto extends BaseViewDto<User> {

    private String email;
    private String username;
    private String firstName;
    private String lastName;


    // @JsonInclude(Include.NON_NULL)
    // private RoleViewDto role;

    public ViewUserDto(User user, List<String> includes) {
        super(user, includes);

        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();

        // if (includes.contains("role")) {
        // include role
        // pass includes to role dto: includes.contains("role.permissions")
        // }
    }
}
