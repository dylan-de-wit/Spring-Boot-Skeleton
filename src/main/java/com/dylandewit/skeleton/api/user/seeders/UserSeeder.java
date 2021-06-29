package com.dylandewit.skeleton.api.user.seeders;

import com.dylandewit.skeleton.api.base.seeders.Seeder;
import com.dylandewit.skeleton.api.role.RoleRepository;
import com.dylandewit.skeleton.api.user.UserRepository;
import com.dylandewit.skeleton.api.user.models.User;
import com.dylandewit.skeleton.exception.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSeeder implements Seeder {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void seed() {
        List<User> users = List.of(
                new User("Dylan", "de Wit", "dylan.de.wit@profit4cloud.nl", "dylan", roleRepository.findByName("Admin").orElseThrow(() -> new NotFoundException("Admin role")))
        );

        users.forEach(user -> {
            if (!userRepository.existsByUsernameOrEmail(user.getEmail(), user.getUsername()))
                userRepository.save(user);
        });
    }
}
