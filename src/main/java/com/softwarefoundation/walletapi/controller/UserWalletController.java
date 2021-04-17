package com.softwarefoundation.walletapi.controller;

import com.softwarefoundation.walletapi.dto.UserWalletDto;
import com.softwarefoundation.walletapi.entity.UserWallet;
import com.softwarefoundation.walletapi.response.Response;
import com.softwarefoundation.walletapi.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user-wallet")
public class UserWalletController {

    @Autowired
    UserWalletService userWalletService;

    @PostMapping
    public ResponseEntity<Response<UserWalletDto>> create(@Valid @RequestBody UserWalletDto dto, BindingResult result){
        Response<UserWalletDto> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        UserWallet userWallet = userWalletService.save(dto.toEntity());
        response.setData(userWallet.toDto());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
