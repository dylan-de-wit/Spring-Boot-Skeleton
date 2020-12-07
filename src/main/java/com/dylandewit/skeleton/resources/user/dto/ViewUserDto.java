package com.dylandewit.skeleton.resources.user.dto;

import com.dylandewit.skeleton.resources.BaseViewDto;
import com.dylandewit.skeleton.resources.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ViewUserDto extends BaseViewDto<User> {

    private Long id;
    private String name;
    private String email;
    private String username;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    // @JsonInclude(Include.NON_NULL)
    // private RoleViewDto role;

    public ViewUserDto(User user, List<String> includes) {
        super(user, includes);

        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.createdOn = user.getCreatedOn();
        this.updatedOn = user.getUpdatedOn();

        // if (includes.contains("role")) {
            // include role
            // pass includes to role dto: includes.contains("role.permissions")
        // }
    }
}
