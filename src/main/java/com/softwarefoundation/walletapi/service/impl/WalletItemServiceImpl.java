package com.softwarefoundation.walletapi.service.impl;

import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.repository.WalletItemRepository;
import com.softwarefoundation.walletapi.service.WalletItemService;
import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WalletItemServiceImpl implements WalletItemService {

    @Value("${quantideDeItensPorPagina}")
    private int quantideDeItensPorPagina;

    @Autowired
    private WalletItemRepository walletItemRepository;

    @Override
    @CacheEvict(value = "findByWalletAndType", allEntries = true)
    public WalletItem save(WalletItem walletItem) {
        return walletItemRepository.save(walletItem);
    }

    @Override
    public Page<WalletItem> findBetweenDates(Long walletId, Date dataInicio, Date dataFim, int pagina) {
        PageRequest pageRequest = PageRequest.of(pagina, quantideDeItensPorPagina);
        return walletItemRepository.findByWalletIdAndAndDataCadastroGreaterThanEqualAndDataCadastroIsLessThanEqual(walletId, dataInicio, dataFim, pageRequest);
    }

    @Override
    @Cacheable(value = "findByWalletAndType")
    public List<WalletItem> findByWalletAndType(Long walletId, TipoEnum tipo) {
        return walletItemRepository.findByWalletIdAndTipo(walletId, tipo);
    }

    @Override
    public BigDecimal sumByWalletId(Long walletId) {
        return walletItemRepository.sumByWalletId(walletId);
    }

    @Override
    public Optional<WalletItem> findById(Long id) {
        return walletItemRepository.findById(id);
    }

    @Override
    @CacheEvict(value = "findByWalletAndType", allEntries = true)
    public void deleteById(Long id) {
        walletItemRepository.deleteById(id);
    }


}
