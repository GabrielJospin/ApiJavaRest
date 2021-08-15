package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.dto.venda.cliente.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.item.ItemVendaRequestDTO;
import com.gvendas.gestaovendas.dto.venda.item.ItemVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import com.gvendas.gestaovendas.entidades.ItemVenda;
import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.entidades.Venda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVendaServico {


    protected VendaResponseDTO criandoVendaRespondeDTO(Venda venda, List<ItemVenda> itemVenda){
        List<ItemVendaResponseDTO> itensVendaRespondeDTO = itemVenda
                .stream().map(item-> criandoItensVendaResponseDTO(item)).collect(Collectors.toList());
        return new VendaResponseDTO(venda.getCodigo(), venda.getData(), itensVendaRespondeDTO);
    }

    protected ClienteVendaResponseDTO criandoClienteVendaResponseDTO(Venda venda, List<ItemVenda> itensVenda){

        return new ClienteVendaResponseDTO(venda.getCliente().getNome(),
                Arrays.asList(criandoVendaRespondeDTO(venda, itensVenda)));
    }

    protected ItemVendaResponseDTO criandoItensVendaResponseDTO(ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
                itemVenda.getProduto().getCodigo(),itemVenda.getProduto().getDescricao());
    }

    protected ItemVenda criandoItemVenda(ItemVendaRequestDTO itemDTO ,Venda venda){
        Produto produto = new Produto(itemDTO.getCodigoProduto());
        return new ItemVenda(itemDTO.getQuantidade(), itemDTO.getPrecoVendido(),produto,venda);
    }
}
