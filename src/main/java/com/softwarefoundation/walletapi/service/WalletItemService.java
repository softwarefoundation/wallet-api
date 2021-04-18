package com.softwarefoundation.walletapi.service;


import com.softwarefoundation.walletapi.entity.WalletItem;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface WalletItemService {

    WalletItem save(WalletItem walletItem);

    Page<WalletItem> findBetweenDates(Long walletId, Date dataInicio, Date dataFim, int pagina);

}
