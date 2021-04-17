package com.softwarefoundation.walletapi.service;

import com.softwarefoundation.walletapi.entity.User;
import com.softwarefoundation.walletapi.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    public  void setUp(){
        BDDMockito.given(userRepository.findByEmailEquals(Mockito.anyString()))
                .willReturn(Optional.of(new User()));
    }

    @Test
    public void testFindByEmail(){
        Optional<User> userRetorno = userService.findByEmail("alice@gmail.com");
        assertTrue(userRetorno.isPresent());
    }

}
