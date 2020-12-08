package com.dylandewit.skeleton.resources.user.dto;

import com.dylandewit.skeleton.annotations.unique_email.UniqueEmail;
import com.dylandewit.skeleton.resources.BaseCreateDto;
import com.dylandewit.skeleton.resources.user.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateUserDto extends BaseCreateDto<User> {

    @NotEmpty
    @Size(max = 50)
    private String username;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @UniqueEmail
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
