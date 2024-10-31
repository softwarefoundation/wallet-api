package com.softwarefoundation.walletapi.entity;

import com.softwarefoundation.walletapi.dto.WalletDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.io.Serializable;

@Data
@Entity
@Table(name = "tb02_wallet")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    public WalletDto toDto(){
        WalletDto dto = new WalletDto();
        dto.setId(getId());
        dto.setNome(getNome());
        return dto;
    }

}
