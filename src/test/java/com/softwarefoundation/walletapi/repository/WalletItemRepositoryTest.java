package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.Wallet;
import com.softwarefoundation.walletapi.entity.WalletItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.util.AssertionErrors.*;

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

    @Test
    public void testSave(){
        Wallet wallet = new Wallet();
        wallet.setNome("Carteira Principal");
        wallet.setValor(BigDecimal.valueOf(125));
        WalletItem walletItem = new WalletItem(1L, wallet, DATA, TIPO, DESCRICAO, VALOR);
        WalletItem walletItemRetorno = walletItemRepository.save(walletItem);
        assertNotNull(walletItemRetorno);
    }

}
