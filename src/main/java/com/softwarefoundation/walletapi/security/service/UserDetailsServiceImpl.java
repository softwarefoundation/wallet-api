package com.softwarefoundation.walletapi.security.service;

import com.softwarefoundation.walletapi.security.UserDetailsDto;
import com.softwarefoundation.walletapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByEmail(username)
                .map(user -> new UserDetailsDto(user))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: ".concat(username)));

    }

}
