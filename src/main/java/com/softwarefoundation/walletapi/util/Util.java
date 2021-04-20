package com.softwarefoundation.walletapi.util;

import com.softwarefoundation.walletapi.entity.User;
import com.softwarefoundation.walletapi.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Util {

    private static UserService staticService;

    public Util(UserService service) {
        Util.staticService = service;
    }

    public static Long getAuthenticatedUserId() {
        try {
            Optional<User> user = staticService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

            if (user.isPresent()) {
                return user.get().getId();
            }else {
                return null;
            }
        }catch (Exception e) {
            return null;
        }
    }
}
