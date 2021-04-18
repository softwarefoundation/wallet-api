package com.softwarefoundation.walletapi.service;

import com.softwarefoundation.walletapi.entity.Wallet;
import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.repository.WalletItemRepository;
import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class WalletItemServiceTest {

    @MockBean
    WalletItemRepository walletItemRepository;

    @Autowired
    WalletItemService walletItemService;

    private static final Date DATA = new Date();
    private static final TipoEnum TIPO = TipoEnum.ENTRADA;
    private static final String DESCRICAO = "Conta de LUZ";
    private static final BigDecimal VALOR = new BigDecimal(100);

    @Test
    public void testSave(){
        BDDMockito.given(walletItemRepository.save(Mockito.any(WalletItem.class))).willReturn(getMockWalletItem());
        WalletItem walletItem = walletItemRepository.save(new WalletItem());

        assertNotNull(walletItem);
        assertEquals(walletItem.getDescricao(), DESCRICAO);
        assertEquals(walletItem.getValor().compareTo(VALOR), 0);
    }

    @Test
    public void testFindBetweenDates(){
        List<WalletItem> walletItems = new ArrayList<>();
        walletItems.add(getMockWalletItem());
        Page<WalletItem> page = new PageImpl<>(walletItems);

        BDDMockito.given(walletItemRepository.findByWalletIdAndAndDataCadastroGreaterThanEqualAndDataCadastroIsLessThanEqual(
                Mockito.anyLong(), Mockito.any(Date.class), Mockito.any(Date.class), Mockito.any(PageRequest.class)
        )).willReturn(page);

        Page<WalletItem> walletItemPage = walletItemService.findBetweenDates(1L, new Date(), new Date(), 0);

        assertNotNull(walletItemPage);
        assertEquals(walletItemPage.getContent().size(), 1);
        assertEquals(walletItemPage.getContent().get(0).getDescricao(), DESCRICAO);
    }

    @Test
    public void testSumByWallet(){
        BigDecimal valor = new BigDecimal(100);
        BDDMockito.given(walletItemRepository.sumByWalletId(Mockito.anyLong())).willReturn(valor);
        BigDecimal total = walletItemService.sumByWalletId(1L);
        assertEquals(total.compareTo(valor), 0);
    }

    private WalletItem getMockWalletItem(){
        Wallet wallet = new Wallet();
        wallet.setId(1L);

        WalletItem walletItem = new WalletItem(1L, wallet, DATA, TIPO, DESCRICAO, VALOR);
        return walletItem;
    }

}
