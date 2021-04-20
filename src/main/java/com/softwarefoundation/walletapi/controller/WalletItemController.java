package com.softwarefoundation.walletapi.controller;

import com.softwarefoundation.walletapi.dto.WalletItemDTO;
import com.softwarefoundation.walletapi.entity.UserWallet;
import com.softwarefoundation.walletapi.entity.Wallet;
import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.response.Response;
import com.softwarefoundation.walletapi.service.UserWalletService;
import com.softwarefoundation.walletapi.service.WalletItemService;
import com.softwarefoundation.walletapi.util.Util;
import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallet-item")
public class WalletItemController {

    @Autowired
    private WalletItemService walletItemService;

    @Autowired
    private UserWalletService userWalletService;

    @PostMapping
    public ResponseEntity<Response<WalletItemDTO>> create(@Valid @RequestBody WalletItemDTO dto, BindingResult result) {

        Response<WalletItemDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        WalletItem wi = walletItemService.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntityToDto(wi));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping(value = "/{walletId}")
    public ResponseEntity<Response<Page<WalletItemDTO>>> findBetweenDates(@PathVariable("walletId") Long walletId,
                                                                          @RequestParam("dataInicio") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataInicio,
                                                                          @RequestParam("dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataFim,
                                                                          @RequestParam(name = "page", defaultValue = "0") int page) {

        Response<Page<WalletItemDTO>> response = new Response<>();

        Optional<UserWallet> userWalletOptional = userWalletService.findByUsersIdAndWalletId(Util.getAuthenticatedUserId(), walletId);

        if (!userWalletOptional.isPresent()) {
            response.getErrors().add("Você não tem acesso a essa carteira");
            return ResponseEntity.badRequest().body(response);
        }

        Page<WalletItem> items = walletItemService.findBetweenDates(walletId, dataInicio, dataFim, page);
        Page<WalletItemDTO> dto = items.map(i -> this.convertEntityToDto(i));
        response.setData(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/tipo/{walletId}")
    public ResponseEntity<Response<List<WalletItemDTO>>> findByWalletIdAndType(@PathVariable("walletId") Long walletId,
                                                                               @RequestParam("tipo") String tipo) {


        Response<List<WalletItemDTO>> response = new Response<>();

        List<WalletItem> list = walletItemService.findByWalletAndType(walletId, TipoEnum.valueOf(tipo));

        List<WalletItemDTO> walletItemDTOS = new ArrayList<>();
        list.forEach(i -> walletItemDTOS.add(this.convertEntityToDto(i)));
        response.setData(walletItemDTOS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/total/{walletId}")
    public ResponseEntity<Response<BigDecimal>> sumByWalletId(@PathVariable("walletId") Long walletId) {

        Response<BigDecimal> response = new Response<>();
        BigDecimal value = walletItemService.sumByWalletId(walletId);
        response.setData(value == null ? BigDecimal.ZERO : value);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<Response<WalletItemDTO>> update(@Valid @RequestBody WalletItemDTO dto, BindingResult result) {

        Response<WalletItemDTO> response = new Response<>();

        Optional<WalletItem> wi = walletItemService.findById(dto.getId());

        if (!wi.isPresent()) {
            result.addError(new ObjectError("WalletItem", "WalletItem não encontrado"));
        } else if (wi.get().getWallet().getId().compareTo(dto.getWalletId()) != 0) {
            result.addError(new ObjectError("WalletItemChanged", "Você não pode alterar a carteira"));
        }

        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        WalletItem saved = walletItemService.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntityToDto(saved));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{walletItemId}")
    public ResponseEntity<Response<String>> delete(@PathVariable("walletItemId") Long walletItemId) {
        Response<String> response = new Response<>();

        Optional<WalletItem> wi = walletItemService.findById(walletItemId);

        if (!wi.isPresent()) {
            response.getErrors().add(MessageFormat.format("WalletItem de id {0} não encontrada", walletItemId));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        walletItemService.deleteById(walletItemId);
        response.setData(MessageFormat.format("WalletItem de id {0} apagada com sucesso", walletItemId));
        return ResponseEntity.ok().body(response);
    }


    private WalletItem convertDtoToEntity(WalletItemDTO dto) {
        WalletItem walletItem = new WalletItem();
        walletItem.setDataCadastro(dto.getDataCadastro());
        walletItem.setDescricao(dto.getDescricao());
        walletItem.setId(dto.getId());
        walletItem.setTipo(TipoEnum.valueOf(dto.getTipo()));
        walletItem.setValor(dto.getValor());

        Wallet wallet = new Wallet();
        wallet.setId(dto.getWalletId());
        walletItem.setWallet(wallet);

        return walletItem;
    }

    private WalletItemDTO convertEntityToDto(WalletItem wi) {
        WalletItemDTO dto = new WalletItemDTO();
        dto.setDataCadastro(wi.getDataCadastro());
        dto.setDescricao(wi.getDescricao());
        dto.setId(wi.getId());
        dto.setTipo(wi.getTipo().name());
        dto.setValor(wi.getValor());
        dto.setWalletId(wi.getWallet().getId());

        return dto;
    }

}
