package com.softwarefoundation.walletapi.dto;

import com.softwarefoundation.walletapi.entity.Wallet;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class WalletDto {

    private Long id;
    @Length(max = 3)
    @NotNull
    private String nome;
    @NotNull
    private BigDecimal valor;

    public Wallet toEntity(){
        Wallet wallet = new Wallet();
        wallet.setId(getId());
        wallet.setNome(getNome());
        wallet.setValor(getValor());
        return wallet;
    }
}
