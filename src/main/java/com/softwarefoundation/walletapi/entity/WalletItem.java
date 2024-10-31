package com.softwarefoundation.walletapi.entity;

import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb04_wallet_item")
public class WalletItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "wallet", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @NotNull(message = "Data é obrigatório")
    @Column(name = "dataCadastro")
    private Date dataCadastro;

    //@Pattern(regexp = "^(ENTRADA|SAIDA)", message = "Os tipos aceitos são: ENTRADA, SAIDA")
    @NotNull(message = "Tipo é obrigatório")
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoEnum tipo;

    @NotNull(message = "Descrição é Obrigatório")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    @Column(name = "valor")
    private BigDecimal valor;

}
