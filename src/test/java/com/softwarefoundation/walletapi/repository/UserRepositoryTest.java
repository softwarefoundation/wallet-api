package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.User;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {

    static final String EMAIL = "alice@gmail.com";

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    public void setUp(){
        User user = new User();
        user.setNome("Alice");
        user.setEmail(EMAIL);
        user.setSenha("123");

        userRepository.save(user);
    }

    @AfterAll
    public void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void testSave() {
        User user = new User();
        user.setNome("Alice");
        user.setEmail(EMAIL);
        user.setSenha("123");

        User userRetorno = userRepository.save(user);

        assertNotNull(userRetorno);
    }

    public void testFindByEmail(){
        Optional<User> userRetorno = userRepository.findByEmail(EMAIL);

        assertTrue(userRetorno.isPresent());
        assertEquals(userRetorno.get().getEmail(), EMAIL);

    }

}
