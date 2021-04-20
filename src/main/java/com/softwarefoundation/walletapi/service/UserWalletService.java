package com.softwarefoundation.walletapi.service;

import com.softwarefoundation.walletapi.entity.UserWallet;

import java.util.Optional;


public interface UserWalletService {
    UserWallet save(UserWallet userWallet);
    Optional<UserWallet> findByUsersIdAndWalletId(Long user, Long wallet);
}
