package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserWalletRepository extends JpaRepository<UserWallet,Long> {

    Optional<UserWallet> findByUsersIdAndWalletId(Long user, Long wallet);

}
