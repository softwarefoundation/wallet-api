package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.Wallet;
import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
public class WalletItemRepositoryTest {

    private static final Date DATA = new Date();
    private static final String TIPO = "ENTRADA";
    private static final String DESCRICAO = "Conta de LUZ";
    private static final BigDecimal VALOR = new BigDecimal(65);

    @Autowired
    WalletItemRepository walletItemRepository;
    @Autowired
    WalletRepository walletRepository;

    @Test
    public void testSave(){
        Wallet wallet = new Wallet();
        wallet.setNome("Carteira Principal");
        wallet.setValor(BigDecimal.valueOf(125));
        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(1L, wallet, DATA, TipoEnum.ENTRADA, DESCRICAO, VALOR);
        WalletItem walletItemRetorno = walletItemRepository.save(walletItem);
        assertNotNull(walletItemRetorno);
        assertEquals(walletItemRetorno.getTipo(), TipoEnum.ENTRADA);
        assertEquals(walletItemRetorno.getDescricao(), DESCRICAO);
        assertEquals(walletItemRetorno.getValor(), VALOR);
        assertEquals(walletItemRetorno.getWallet().getId(), wallet.getId());
    }

}
