package com.gvendas.gestaovendas.dto.venda;

import com.gvendas.gestaovendas.dto.venda.item.ItemVendaRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.time.LocalDate;

@ApiModel("Venda Requisição DTO")
public class VendaRequestDTO {

    @ApiModelProperty(value = "Data")
    @NotNull(message = "data")
    private LocalDate data;

    @ApiModelProperty(value = "Itens da venda")
    @NotNull(message = "intens venda")
    @Valid
    private List<ItemVendaRequestDTO> itensVendaDTO;


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaRequestDTO> getItensVendaDTO() {
        return itensVendaDTO;
    }

    public void setItensVendaDTO(List<ItemVendaRequestDTO> itensVendaDTO) {
        this.itensVendaDTO = itensVendaDTO;
    }
}
