package com.softwarefoundation.walletapi.entity;

import com.softwarefoundation.walletapi.dto.WalletDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb02_wallet")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private BigDecimal valor;

    public WalletDto toDto(){
        WalletDto dto = new WalletDto();
        dto.setId(getId());
        dto.setNome(getNome());
        dto.setValor(getValor());
        return dto;
    }

}
