package com.softwarefoundation.walletapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwarefoundation.walletapi.dto.WalletItemDTO;
import com.softwarefoundation.walletapi.entity.User;
import com.softwarefoundation.walletapi.entity.Wallet;
import com.softwarefoundation.walletapi.entity.WalletItem;
import com.softwarefoundation.walletapi.service.WalletItemService;
import com.softwarefoundation.walletapi.util.enums.TipoEnum;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class WalletItemControllerTest {

    @MockBean
    WalletItemService walletItemService;

    @Autowired
    MockMvc mvc;


    private static final Long ID = 1L;
    private static final Date DATA_CADASTRO = new Date();
    private static final LocalDate HOJE = LocalDate.now();
    private static final TipoEnum TIPO = TipoEnum.ENTRADA;
    private static final String DESCRICAO = "Conta de Luz";
    private static final BigDecimal VALOR = BigDecimal.valueOf(65);
    private static final String URL = "/wallet-item";

    @Test
    public void testSave() throws Exception {

        BDDMockito.given(walletItemService.save(Mockito.any(WalletItem.class))).willReturn(getMockWalletItem());

        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.dataCadastro").value(HOJE.format(getDateFormater())))
                .andExpect(jsonPath("$.data.descricao").value(DESCRICAO))
                .andExpect(jsonPath("$.data.tipo").value(TIPO.name()))
                .andExpect(jsonPath("$.data.valor").value(VALOR))
                .andExpect(jsonPath("$.data.walletId").value(ID));

    }

    @Test
    public void testFindBetweenDates() throws Exception {
        List<WalletItem> list = new ArrayList<>();
        list.add(getMockWalletItem());
        Page<WalletItem> page = new PageImpl(list);

        String dataInicio = HOJE.format(getDateFormater());
        String dataFim = HOJE.plusDays(5).format(getDateFormater());

        User user = new User();
        user.setId(1L);

        BDDMockito.given(walletItemService.findBetweenDates(Mockito.anyLong(), Mockito.any(Date.class), Mockito.any(Date.class), Mockito.anyInt())).willReturn(page);


        mvc.perform(MockMvcRequestBuilders.get(URL + "/1?dataInicio=" + dataInicio + "&dataFim=" + dataFim)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content[0].id").value(ID))
                .andExpect(jsonPath("$.data.content[0].dataCadastro").value(HOJE.format(getDateFormater())))
                .andExpect(jsonPath("$.data.content[0].descricao").value(DESCRICAO))
                .andExpect(jsonPath("$.data.content[0].tipo").value(TIPO.name()))
                .andExpect(jsonPath("$.data.content[0].valor").value(VALOR))
                .andExpect(jsonPath("$.data.content[0].walletId").value(ID));

    }

    @Test
    public void testFindByType() throws Exception {
        List<WalletItem> list = new ArrayList<>();
        list.add(getMockWalletItem());

        BDDMockito.given(walletItemService.findByWalletAndType(Mockito.anyLong(), Mockito.any(TipoEnum.class))).willReturn(list);

        mvc.perform(MockMvcRequestBuilders.get(URL+"/tipo/1?tipo=ENTRADA")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.[0].id").value(ID))
                .andExpect(jsonPath("$.data.[0].dataCadastro").value(HOJE.format(getDateFormater())))
                .andExpect(jsonPath("$.data.[0].descricao").value(DESCRICAO))
                .andExpect(jsonPath("$.data.[0].tipo").value(TIPO.name()))
                .andExpect(jsonPath("$.data.[0].valor").value(VALOR))
                .andExpect(jsonPath("$.data.[0].walletId").value(ID));

    }

    @Test
    public void testSumByWallet() throws Exception {
        BigDecimal value = BigDecimal.valueOf(536.90);

        BDDMockito.given(walletItemService.sumByWalletId(Mockito.anyLong())).willReturn(value);

        mvc.perform(MockMvcRequestBuilders.get(URL+"/total/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("536.9"));

    }

    @Test
    public void testUpdate() throws Exception {

        String descricao = "Nova descrição";
        Wallet w = new Wallet();
        w.setId(ID);

        BDDMockito.given(walletItemService.findById(Mockito.anyLong())).willReturn(Optional.of(getMockWalletItem()));
        BDDMockito.given(walletItemService.save(Mockito.any(WalletItem.class))).willReturn(new WalletItem(1L, w, DATA_CADASTRO, TipoEnum.SAIDA, descricao, VALOR));

        mvc.perform(MockMvcRequestBuilders.put(URL).content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.dataCadastro").value(HOJE.format(getDateFormater())))
                .andExpect(jsonPath("$.data.descricao").value(descricao))
                .andExpect(jsonPath("$.data.tipo").value(TipoEnum.SAIDA.name()))
                .andExpect(jsonPath("$.data.valor").value(VALOR))
                .andExpect(jsonPath("$.data.walletId").value(ID));

    }

    @Test
    public void testUpdateWalletChange() throws Exception {

        Wallet w = new Wallet();
        w.setId(99L);

        WalletItem wi = new WalletItem(1L, w, DATA_CADASTRO, TipoEnum.SAIDA, DESCRICAO, VALOR);

        BDDMockito.given(walletItemService.findById(Mockito.anyLong())).willReturn(Optional.of(wi));

        mvc.perform(MockMvcRequestBuilders.put(URL).content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors[0]").value("Você não pode alterar a carteira"));

    }

    @Test
    public void testUpdateInvalidId() throws Exception {

        BDDMockito.given(walletItemService.findById(Mockito.anyLong())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.put(URL).content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors[0]").value("WalletItem não encontrado"));

    }

    @Test
    public void testDelete() throws JsonProcessingException, Exception {

        BDDMockito.given(walletItemService.findById(Mockito.anyLong())).willReturn(Optional.of(new WalletItem()));

        mvc.perform(MockMvcRequestBuilders.delete(URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("WalletItem de id "+ ID +" apagada com sucesso"));
    }

    @Test
    public void testDeleteInvalid() throws Exception {

        BDDMockito.given(walletItemService.findById(Mockito.anyLong())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.delete(URL+"/99")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors[0]").value("WalletItem de id "+ 99 + " não encontrada"));

    }

    private WalletItem getMockWalletItem() {
        Wallet wallet = new Wallet();
        wallet.setId(1L);

        WalletItem walletItem = new WalletItem(1L, wallet, DATA_CADASTRO, TIPO, DESCRICAO, VALOR);
        return walletItem;
    }

    public String getJsonPayload() throws JsonProcessingException {
        WalletItemDTO dto = new WalletItemDTO();
        dto.setId(ID);
        dto.setDataCadastro(DATA_CADASTRO);
        dto.setDescricao(DESCRICAO);
        dto.setTipo(TIPO.name());
        dto.setValor(VALOR);
        dto.setWalletId(ID);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

    private DateTimeFormatter getDateFormater() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return formatter;
    }


}
