package com.softwarefoundation.walletapi.dto;

import com.softwarefoundation.walletapi.entity.User;
import com.softwarefoundation.walletapi.entity.UserWallet;
import com.softwarefoundation.walletapi.entity.Wallet;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserWalletDto implements Serializable {

    private Long id;

    @NotNull(message = "Informe o ID do Usuário")
    private Long user;

    @NotNull(message = "Informe o ID da carteira")
    private Long wallet;

    public UserWallet toEntity(){
        UserWallet userWallet = new UserWallet();
        userWallet.setId(getId());
        User user = new User();
        user.setId(getUser());
        userWallet.setUser(user);
        Wallet wallet = new Wallet();
        wallet.setId(getWallet());
        userWallet.setWallet(wallet);
        return userWallet;
    }

}
