package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailEquals(final String email);

}
