package com.softwarefoundation.walletapi.dto;

import com.softwarefoundation.walletapi.entity.Wallet;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class WalletDto {

    private Long id;

    @Length(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    @NotNull(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O valor é obrigatório")
    private BigDecimal valor;

    public Wallet toEntity(){
        Wallet wallet = new Wallet();
        wallet.setId(getId());
        wallet.setNome(getNome());
        wallet.setValor(getValor());
        return wallet;
    }
}
