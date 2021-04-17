package com.softwarefoundation.walletapi.entity;

import com.softwarefoundation.walletapi.dto.UserWalletDto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb03_user_wallet")
public class UserWallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "wallet", referencedColumnName = "id")
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
