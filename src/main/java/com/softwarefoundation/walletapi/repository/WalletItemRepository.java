package com.softwarefoundation.walletapi.repository;

import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface WalletItemRepository extends JpaRepository<WalletItem,Long> {

    Page<WalletItem> findByWalletIdAndAndDataCadastroGreaterThanEqualAndDataCadastroIsLessThanEqual(Long walletId, Date dataInicial, Date dataFinal, Pageable pageable);

    List<WalletItem> findByWalletIdAndTipo(Long walletId, TipoEnum tipoEnum);

}
