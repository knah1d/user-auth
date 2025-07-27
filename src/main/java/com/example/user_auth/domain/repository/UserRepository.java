package com.example.user_auth.domain.repository;

import com.example.user_auth.domain.model.AppUser;
import java.util.Optional;

public interface UserRepository {
    AppUser save(AppUser user);

    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteById(Long id);

    Optional<AppUser> findById(Long id);
}
