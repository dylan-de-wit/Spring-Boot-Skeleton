package com.dylandewit.skeleton.annotations.unique_username;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Documented
public @interface UniqueUsername {

    String message() default "should be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}