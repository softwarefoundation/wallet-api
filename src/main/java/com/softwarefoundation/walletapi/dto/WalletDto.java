package com.softwarefoundation.walletapi.dto;

import com.softwarefoundation.walletapi.entity.Wallet;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class WalletDto {

    private Long id;

    @Length(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    @NotNull(message = "O nome é obrigatório")
    private String nome;

    public Wallet toEntity(){
        Wallet wallet = new Wallet();
        wallet.setId(getId());
        wallet.setNome(getNome());
        return wallet;
    }
}
