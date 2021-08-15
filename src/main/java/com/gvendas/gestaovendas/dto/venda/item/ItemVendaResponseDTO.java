package com.gvendas.gestaovendas.dto.venda.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Item Venda Responde DTO")
public class ItemVendaResponseDTO {

    @ApiModelProperty(value = "Código")
    private Long codigo;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço vendido")
    private BigDecimal precoVendido;

    @ApiModelProperty(value = "codigo Produt")
    private Long codigoProduto;

    @ApiModelProperty(value = "Descrção")
    private String descricaoProduto;

    public ItemVendaResponseDTO(Long codigo, Integer quantidade, BigDecimal precoVenda,
                                Long codigoProduto, String descricaoProduto) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.precoVendido = precoVenda;
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
}
