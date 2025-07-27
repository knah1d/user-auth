package com.example.user_auth.domain.service;
import com.example.user_auth.domain.model.User;
public interface UserService {
    User register(String username, String password);
    User authenticate(String username, String password);
}
