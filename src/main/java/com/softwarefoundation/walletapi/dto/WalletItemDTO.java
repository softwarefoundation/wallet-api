package com.softwarefoundation.walletapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


import java.math.BigDecimal;
import java.util.Date;

@Data
public class WalletItemDTO {

    private long id;

    @NotNull(message = "Insira o id da carteira")
    private Long walletId;

    @NotNull(message = "Informe uma data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataCadastro;

    @NotNull(message = "Informe um tipo")
    @Pattern(regexp="^(ENTRADA|SAÍDA)$", message = "Para o tipo somente são aceitos os valores ENTRADA ou SAÍDA")
    private String tipo;

    @NotNull(message = "Informe uma descrição")
    @Length(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
    private String descricao;

    @NotNull(message = "Informe um valor")
    private BigDecimal valor;
}
