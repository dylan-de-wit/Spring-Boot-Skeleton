package com.dylandewit.skeleton.api.user.seeders;

import com.dylandewit.skeleton.api.base.seeders.Seeder;
import com.dylandewit.skeleton.api.user.UserRepository;
import com.dylandewit.skeleton.api.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FakeUserSeeder implements Seeder {
    private final UserRepository userRepository;
    private final UserFaker userFaker;

    @Autowired
    public FakeUserSeeder(UserRepository userRepository, UserFaker userFaker) {
        this.userRepository = userRepository;
        this.userFaker = userFaker;
    }

    @Override
    public void seed() {
        List<User> users = List.of(userFaker.make(), userFaker.make(), userFaker.make());

        userRepository.saveAll(users);
    }
}
