package com.softwarefoundation.walletapi.service.impl;

import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.repository.WalletItemRepository;
import com.softwarefoundation.walletapi.service.WalletItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WalletItemServiceImpl implements WalletItemService {

    @Value("${quantideDeItensPorPagina}")
    private int quantideDeItensPorPagina;

    @Autowired
    private WalletItemRepository walletItemRepository;

    @Override
    public WalletItem save(WalletItem walletItem) {
        return walletItemRepository.save(walletItem);
    }

    @Override
    public Page<WalletItem> findBetweenDates(Long walletId, Date dataInicio, Date dataFim, int pagina) {
        PageRequest pageRequest = PageRequest.of(pagina, quantideDeItensPorPagina);
        return walletItemRepository.findByWalletIdAndAndDataCadastroGreaterThanEqualAndDataCadastroIsLessThanEqual(walletId, dataInicio, dataFim, pageRequest);
    }
}
