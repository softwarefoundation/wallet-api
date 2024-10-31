package com.softwarefoundation.walletapi.controller;

import com.softwarefoundation.walletapi.dto.WalletDto;
import com.softwarefoundation.walletapi.entity.Wallet;
import com.softwarefoundation.walletapi.response.Response;
import com.softwarefoundation.walletapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @PostMapping
    public ResponseEntity<Response<WalletDto>> create (@Valid @RequestBody WalletDto dto, BindingResult result){
        Response<WalletDto> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Wallet wallet = walletService.save(dto.toEntity());
        response.setData(wallet.toDto());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
