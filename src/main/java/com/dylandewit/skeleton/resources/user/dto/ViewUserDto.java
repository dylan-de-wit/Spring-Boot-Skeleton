package com.dylandewit.skeleton.resources.user.dto;

import com.dylandewit.skeleton.helpers.IncludesHelper;
import com.dylandewit.skeleton.resources.base.dto.BaseViewDto;
import com.dylandewit.skeleton.resources.user.models.User;
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
        includes = IncludesHelper.fetchNestedIncludes(includes, "user");

        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();

        if (IncludesHelper.hasInclude(includes, "role"))
            System.out.println("hoi"); // this.role = new ViewRoleDto(user.getRole(), includes)
    }
}
