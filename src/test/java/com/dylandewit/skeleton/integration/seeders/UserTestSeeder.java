package com.dylandewit.skeleton.integration.seeders;

import com.dylandewit.skeleton.resources.user.UserRepository;
import com.dylandewit.skeleton.resources.user.models.User;
import com.dylandewit.skeleton.resources.user.seeders.UserFaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.List;

@TestComponent
public class UserTestSeeder implements TestSeeder<User> {
    private final UserRepository userRepository;
    private final UserFaker userFaker;

    @Autowired
    public UserTestSeeder(UserRepository userRepository, UserFaker userFaker) {
        this.userRepository = userRepository;
        this.userFaker = userFaker;
    }

    @Override
    public User seedOne() {
        return userRepository.save(userFaker.make());
    }

    @Override
    public List<User> seedMultiple() {
        List<User> users = List.of(userFaker.make(), userFaker.make(), userFaker.make());

        List<User> saved = new ArrayList<>();
        userRepository.saveAll(users).iterator().forEachRemaining(users::add);

        return saved;
    }
}
