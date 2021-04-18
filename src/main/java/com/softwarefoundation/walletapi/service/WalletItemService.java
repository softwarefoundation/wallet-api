package com.softwarefoundation.walletapi.service;


import com.softwarefoundation.walletapi.entity.WalletItem;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;

public interface WalletItemService {

    WalletItem save(WalletItem walletItem);

    Page<WalletItem> findBetweenDates(Long walletId, Date dataInicio, Date dataFim, int pagina);

    BigDecimal sumByWalletId(Long walletId);
}