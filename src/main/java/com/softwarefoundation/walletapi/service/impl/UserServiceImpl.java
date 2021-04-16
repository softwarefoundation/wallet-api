package com.softwarefoundation.walletapi.service.impl;

import com.softwarefoundation.walletapi.entity.User;
import com.softwarefoundation.walletapi.repository.UserRepository;
import com.softwarefoundation.walletapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailEquals(email);
    }
}
