package com.softwarefoundation.walletapi.service;


import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WalletItemService {

    WalletItem save(WalletItem walletItem);

    Page<WalletItem> findBetweenDates(Long walletId, Date dataInicio, Date dataFim, int pagina);

    List<WalletItem> findByWalletAndType(Long wallet, TipoEnum tipo);

    BigDecimal sumByWalletId(Long walletId);

    Optional<WalletItem> findById(Long id);

    void deleteById(Long id);
}
