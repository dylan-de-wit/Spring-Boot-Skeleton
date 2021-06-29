package com.dylandewit.skeleton.config;

import com.dylandewit.skeleton.api.user.UserRepository;
import com.dylandewit.skeleton.api.user.models.User;
import com.dylandewit.skeleton.exception.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

@Configuration
public class CurrentUserConfig {
    private final UserRepository userRepository;

    @Autowired
    public CurrentUserConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public Function<Object, User> fetchUser() {
        return (principal -> {
            if (principal instanceof UserDetails) {
                return userRepository.findFirstByUsername(((UserDetails) principal).getUsername()).orElseThrow(() -> new NotFoundException("user"));
            } else {
                return userRepository.findFirstByUsername((String) principal).orElseThrow(() -> new NotFoundException("user"));
            }
        });
    }
}
