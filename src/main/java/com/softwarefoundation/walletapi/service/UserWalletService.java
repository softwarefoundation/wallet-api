package com.softwarefoundation.walletapi.service;

import com.softwarefoundation.walletapi.entity.UserWallet;
import org.springframework.stereotype.Service;

@Service
public interface UserWalletService {
    UserWallet save(UserWallet userWallet);
}
