package com.softwarefoundation.walletapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

public class Bcrypt {

    public static String getHash(String senha){
        return Objects.isNull(senha) ? null : new BCryptPasswordEncoder().encode(senha);
    }

}
