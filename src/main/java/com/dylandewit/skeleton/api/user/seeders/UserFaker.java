package com.dylandewit.skeleton.api.user.seeders;

import com.dylandewit.skeleton.api.base.seeders.FakerContract;
import com.dylandewit.skeleton.api.user.models.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class UserFaker implements FakerContract<User> {
    public User make() {
        Faker faker = new Faker();

        return new User(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.name().username(), null);
    }
}
