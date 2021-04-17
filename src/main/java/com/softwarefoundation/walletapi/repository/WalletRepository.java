package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
