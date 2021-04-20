package com.softwarefoundation.walletapi.entity;

import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
