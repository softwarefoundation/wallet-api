package com.softwarefoundation.walletapi.service.impl;

import com.softwarefoundation.walletapi.entity.Wallet;
import com.softwarefoundation.walletapi.repository.WalletRepository;
import com.softwarefoundation.walletapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}
