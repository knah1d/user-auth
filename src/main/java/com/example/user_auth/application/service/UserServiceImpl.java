package com.example.user_auth.application.service;

import com.example.user_auth.domain.model.User;
import com.example.user_auth.domain.model.AppUser;
import com.example.user_auth.domain.service.UserService;
import com.example.user_auth.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        AppUser entity = new AppUser(username, encoder.encode(password));
        return userRepository.save(entity).toDomain();
    }

    @Override
    public User authenticate(String username, String password) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user.toDomain();
    }
}
