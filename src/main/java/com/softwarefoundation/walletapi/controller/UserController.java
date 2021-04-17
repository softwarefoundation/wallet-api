package com.softwarefoundation.walletapi.controller;

import com.softwarefoundation.walletapi.dto.UserDto;
import com.softwarefoundation.walletapi.entity.User;
import com.softwarefoundation.walletapi.service.UserService;
import com.softwarefoundation.walletapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Response<UserDto>> create(@Valid @RequestBody UserDto dto, BindingResult result){

        Response<UserDto> response = new Response<UserDto>();

        if(result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = userService.save(this.dtoToEntity(dto));
        response.setData(this.entityToDto(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private User dtoToEntity(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        return user;
    }

    private UserDto entityToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setSenha(user.getSenha());
        return dto;
    }

}
