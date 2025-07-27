package com.example.user_auth.infrastructure.persistence;

import com.example.user_auth.domain.model.AppUser;
import com.example.user_auth.domain.repository.UserRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public AppUser save(AppUser user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return jpaUserRepository.findById(id);
    }
}
