package com.dylandewit.skeleton.annotations.unique_username;

import com.dylandewit.skeleton.resources.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    @Autowired
    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username == null || userService.existsByUsername(username);
    }
}