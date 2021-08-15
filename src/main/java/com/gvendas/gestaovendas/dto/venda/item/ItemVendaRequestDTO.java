package com.gvendas.gestaovendas.dto.venda.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Itens da Venda Requisição DTO")
public class ItemVendaRequestDTO {

    @ApiModelProperty(value = "Codigo do Produto")
    @NotNull(message = "codigoProduto")
    private Long codigoProduto;

    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "quantidade")
    @Min(value = 1, message = "quantidade")
    private Integer quantidade;

    @ApiModelProperty("Preço Vendido")
    @NotNull(message = "PrecoVendido")

    private BigDecimal precoVendido;


    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoVendido() {
        return precoVendido;
    }

    public void setPrecoVendido(BigDecimal precoVendido) {
        this.precoVendido = precoVendido;
    }
}
