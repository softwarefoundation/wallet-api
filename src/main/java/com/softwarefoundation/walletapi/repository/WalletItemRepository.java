package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.WalletItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletItemRepository extends JpaRepository<WalletItem,Long> {
}
