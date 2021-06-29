package com.dylandewit.skeleton.api.user.seeders;

import com.dylandewit.skeleton.api.base.seeders.FakerContract;
import com.dylandewit.skeleton.api.role.seeders.RoleFaker;
import com.dylandewit.skeleton.api.user.models.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserFaker implements FakerContract<User> {
    private final RoleFaker roleFaker;

    @Autowired
    public UserFaker(RoleFaker roleFaker) {
        this.roleFaker = roleFaker;
    }

    public User make() {
        Faker faker = new Faker();

        return new User(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.name().username(), roleFaker.make());
    }

    @Override
    public Set<User> makeMultiple() {
        return new HashSet<>(Set.of(make(), make(), make()));
    }
}
