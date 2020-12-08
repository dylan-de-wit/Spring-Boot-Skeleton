package com.dylandewit.skeleton.resources.user.seeders;

import com.dylandewit.skeleton.resources.base.seeders.Seeder;
import com.dylandewit.skeleton.resources.user.UserRepository;
import com.dylandewit.skeleton.resources.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSeeder implements Seeder {
    private final UserRepository userRepository;

    @Autowired
    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void seed() {
        List<User> users = List.of(
                new User("Dylan", "de Wit", "dylan.de.wit@profit4cloud.nl", "dylan")
        );

        users.forEach(user -> {
            if (!userRepository.existsByUsernameOrEmail(user.getEmail(), user.getUsername()))
                userRepository.save(user);
        });
    }
}
