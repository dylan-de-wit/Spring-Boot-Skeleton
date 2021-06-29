package com.dylandewit.skeleton.api.user.dto;

import com.dylandewit.skeleton.annotations.unique_username.UniqueUsername;
import com.dylandewit.skeleton.api.base.dto.BasePostDto;
import com.dylandewit.skeleton.api.user.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPostDto extends BasePostDto<User> {

    @NotEmpty
    @Size(max = 50)
    @UniqueUsername
    private String username;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String email;

    @Override
    public User toModel() {
        return User.builder()
                .email(email)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
