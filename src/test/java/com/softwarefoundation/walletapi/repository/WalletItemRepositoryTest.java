package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.Wallet;
import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class WalletItemRepositoryTest {

    private static final Date DATA = new Date();
    private static final TipoEnum TIPO = TipoEnum.ENTRADA;
    private static final String DESCRICAO = "Conta de LUZ";
    private static final BigDecimal VALOR = new BigDecimal(65);
    private long walletId = 0;
    private long walletItemId = 0;

    @Autowired
    WalletItemRepository walletItemRepository;
    @Autowired
    WalletRepository walletRepository;

    @BeforeEach
    public void setUp(){
        Wallet wallet = new Wallet();
        wallet.setNome("Carteira Principal");
        wallet.setValor(BigDecimal.valueOf(250));
        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(null, wallet, DATA, TipoEnum.ENTRADA, DESCRICAO, VALOR);
        WalletItem walletItemRetorno = walletItemRepository.save(walletItem);
        walletId = wallet.getId();
        walletItemId = walletItemRetorno.getId();
    }

    @AfterEach
    public void tearDown(){
        walletItemRepository.deleteAll();
        walletRepository.deleteAll();
    }

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

    @Test
    public void testSaveInvalidWalletItem(){
        assertThrows(ConstraintViolationException.class, () -> {
            WalletItem walletItem = new WalletItem(null, null, DATA, null, DESCRICAO, VALOR);
            walletItemRepository.save(walletItem);
        });
    }

    @Test
    public void testUpdate(){
        Optional<WalletItem> walletItemOptional = walletItemRepository.findById(walletItemId);
        String novaDecricao = "Caterira Reserva";
        WalletItem item = walletItemOptional.get();
        item.setDescricao(novaDecricao);
        walletItemRepository.save(item);

        Optional<WalletItem> walletItemOptional2 = walletItemRepository.findById(walletItemId);
        assertEquals(walletItemOptional2.get().getDescricao(), novaDecricao);

    }

    @Test
    public void deleteWalletItem(){
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        WalletItem walletItem = new WalletItem(null, walletOptional.get(), DATA, TipoEnum.ENTRADA, DESCRICAO, VALOR);
        walletItemRepository.save(walletItem);
        walletItemRepository.deleteById(walletItem.getId());

        Optional<WalletItem> walletItemOptional = walletItemRepository.findById(walletItem.getId());
        assertFalse(walletItemOptional.isPresent());
    }

    @Test
    public void findBetweenDates(){
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        LocalDateTime localDateTime = DATA.toInstant().atZone(ZoneOffset.systemDefault()).toLocalDateTime();
        Date dataAtualMais5Dias = Date.from(localDateTime.plusDays(5).atZone(ZoneId.systemDefault()).toInstant());
        Date dataAtualMais7Dias = Date.from(localDateTime.plusDays(7).atZone(ZoneId.systemDefault()).toInstant());

        walletItemRepository.save(new WalletItem(null, walletOptional.get(),dataAtualMais5Dias ,TipoEnum.ENTRADA,DESCRICAO,VALOR));
        walletItemRepository.save(new WalletItem(null, walletOptional.get(),dataAtualMais7Dias ,TipoEnum.ENTRADA,DESCRICAO,VALOR));

        PageRequest pageRequest =  PageRequest.of(0,10);
        Page<WalletItem> walletItemsPage = walletItemRepository.findByWalletIdAndAndDataCadastroGreaterThanEqualAndDataCadastroIsLessThanEqual(walletId, DATA, dataAtualMais5Dias, pageRequest);

        assertEquals(walletItemsPage.getContent().size(),2);
        assertEquals(walletItemsPage.getTotalElements(),2);
        assertEquals(walletItemsPage.getContent().get(0).getWallet().getId(), walletId);
    }

    @Test
    public void testFindByTipo(){
        List<WalletItem> walletItems = walletItemRepository.findByWalletIdAndTipo(walletId,TIPO);

        assertEquals(walletItems.size(),1);
        assertEquals(walletItems.get(0).getTipo(),TIPO);

    }

    @Test
    public void testFindByTipoSaida(){
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        walletItemRepository.save(new WalletItem(null, walletOptional.get(), DATA, TipoEnum.SAIDA, DESCRICAO,VALOR));

        List<WalletItem> walletItems = walletItemRepository.findByWalletIdAndTipo(walletId, TipoEnum.SAIDA);

        assertEquals(walletItems.size(), 1);
        assertEquals(walletItems.get(0).getTipo(), TipoEnum.SAIDA);
    }



}
