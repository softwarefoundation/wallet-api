package com.softwarefoundation.walletapi.service.impl;

import com.softwarefoundation.walletapi.entity.UserWallet;
import com.softwarefoundation.walletapi.repository.UserWalletRepository;
import com.softwarefoundation.walletapi.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserWalletImpl implements UserWalletService {

    @Autowired
    private UserWalletRepository userWalletRepository;

    @Override
    public UserWallet save(UserWallet userWallet) {
        return userWalletRepository.save(userWallet);
    }
}
