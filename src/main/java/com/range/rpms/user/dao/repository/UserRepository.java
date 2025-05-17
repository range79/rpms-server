package com.range.rpms.user.dao.repository;

import com.range.rpms.user.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
