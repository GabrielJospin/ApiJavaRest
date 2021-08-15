package com.gvendas.gestaovendas.dto.venda;

import com.gvendas.gestaovendas.dto.venda.item.ItemVendaResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda Retorno DTO")
public class VendaResponseDTO {

    @ApiModelProperty(value = "codigo")
    private Long codigo;

    @ApiModelProperty(value = "data")
    private LocalDate data;

    @ApiModelProperty(value = "itens da Venda")
    private List<ItemVendaResponseDTO> itemVendaDTO;

    public VendaResponseDTO(Long codigo, LocalDate data, List<ItemVendaResponseDTO> itemVendaDTO) {
        this.codigo = codigo;
        this.data = data;
        this.itemVendaDTO = itemVendaDTO;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaResponseDTO> getItemVendaDTO() {
        return itemVendaDTO;
    }

    public void setItemVendaDTO(List<ItemVendaResponseDTO> itemVendaDTO) {
        this.itemVendaDTO = itemVendaDTO;
    }

}
