package com.softwarefoundation.walletapi.entity;

import com.softwarefoundation.walletapi.dto.UserWalletDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


import java.io.Serializable;

@Data
@Entity
@Table(name = "tb03_user_wallet")
public class UserWallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    public UserWalletDto toDto(){
        UserWalletDto userWalletDto = new UserWalletDto();
        userWalletDto.setId(getId());
        userWalletDto.setUser(getUser().getId());
        userWalletDto.setWallet(getWallet().getId());
        return userWalletDto;
    }
}
