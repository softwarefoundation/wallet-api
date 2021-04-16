package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testSave() {
        User user = new User();
        user.setNome("Alice");
        user.setEmail("alice@gmail.com");
        user.setSenha("123");

        User userRetorno = userRepository.save(user);

        Assertions.assertNull(userRetorno);
    }

}
