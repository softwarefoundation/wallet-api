package com.softwarefoundation.walletapi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserWalletDto implements Serializable {

    private Long id;

    @NotNull(message = "Informe o ID do Usuário")
    private Long user;

    @NotNull(message = "Informe o ID da carteira")
    private Long wallet;

}
