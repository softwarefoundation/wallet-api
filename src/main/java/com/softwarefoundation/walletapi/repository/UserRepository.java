package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
