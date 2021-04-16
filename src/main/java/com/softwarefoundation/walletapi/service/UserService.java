package com.softwarefoundation.walletapi.service;

import com.softwarefoundation.walletapi.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByEmail(final String email);

}
