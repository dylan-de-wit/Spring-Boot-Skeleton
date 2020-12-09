package com.dylandewit.skeleton.resources.user.seeders;

import com.dylandewit.skeleton.exception.exceptions.NotFoundException;
import com.dylandewit.skeleton.resources.base.seeders.Seeder;
import com.dylandewit.skeleton.resources.role.RoleRepository;
import com.dylandewit.skeleton.resources.user.UserRepository;
import com.dylandewit.skeleton.resources.user.models.User;
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
