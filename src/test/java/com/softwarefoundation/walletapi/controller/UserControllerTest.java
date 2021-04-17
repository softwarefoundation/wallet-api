package com.softwarefoundation.walletapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwarefoundation.walletapi.dto.UserDto;
import com.softwarefoundation.walletapi.entity.User;
import com.softwarefoundation.walletapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    static final String NOME = "Alice";
    static final String EMAIL = "alice@gmail.com";
    static final String SENHA = "123";
    static final String URL = "/user";

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    public void testSave() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    public User getUserMock(){
        User user = new User();
        user.setNome(NOME);
        user.setEmail(EMAIL);
        user.setSenha(SENHA);
        return user;
    }

    public String getJsonPayload() throws JsonProcessingException {
        UserDto dto = new UserDto();
        dto.setNome(NOME);
        dto.setEmail(EMAIL);
        dto.setSenha(SENHA);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dto);
    }

}
