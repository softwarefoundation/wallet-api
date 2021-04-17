package com.softwarefoundation.walletapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwarefoundation.walletapi.dto.UserDto;
import com.softwarefoundation.walletapi.entity.User;
import com.softwarefoundation.walletapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
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

    static final Long ID = 1L;
    static final String NOME = "Alice";
    static final String EMAIL = "alice@gmail.com";
    static final String SENHA = "123456";
    static final String URL = "/user";

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testSave() throws Exception {
        BDDMockito.given(userService.save(Mockito.any(User.class))).willReturn(getUserMock());
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(getJsonPayload(ID, NOME, EMAIL, SENHA))
                .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(ID))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.nome").value(NOME))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value(EMAIL))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.senha").value(SENHA));
    }

    @Test
    public void testSaveInvalidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(getJsonPayload(ID, NOME, "email-inavalido", SENHA))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("Email inválido"));
    }

    public User getUserMock(){
        User user = new User();
        user.setId(ID);
        user.setNome(NOME);
        user.setEmail(EMAIL);
        user.setSenha(SENHA);
        return user;
    }

    public String getJsonPayload(Long id, String nome, String email, String senha) throws JsonProcessingException {
        UserDto dto = new UserDto();
        dto.setId(id);
        dto.setNome(nome);
        dto.setEmail(email);
        dto.setSenha(senha);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dto);
    }

}
