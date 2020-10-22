package com.dylandewit.skeleton.resources.user;

import com.dylandewit.skeleton.resources.BaseService;
import com.dylandewit.skeleton.resources.user.dto.CreateUserDto;
import com.dylandewit.skeleton.resources.user.dto.ViewUserDto;
import com.dylandewit.skeleton.resources.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends BaseService<User, ViewUserDto, CreateUserDto> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);

        this.userRepository = userRepository;
    }

    @Override
    protected ViewUserDto mapToDto(User user, List<String> includes) {
        return new ViewUserDto(user, includes);
    }

    @Override
    protected User mapForUpdate(User user, CreateUserDto createUserDto) {
        return user;
    }

    public Optional<User> findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
