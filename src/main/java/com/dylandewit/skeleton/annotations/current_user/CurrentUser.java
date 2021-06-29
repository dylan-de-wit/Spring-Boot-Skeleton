package com.dylandewit.skeleton.annotations.current_user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal(expression = "@fetchCurrentUser.apply(#this)", errorOnInvalidType=true)
public @interface CurrentUser {}