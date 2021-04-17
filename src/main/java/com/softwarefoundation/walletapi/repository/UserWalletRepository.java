package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWalletRepository extends JpaRepository<UserWallet,Long> {
}
