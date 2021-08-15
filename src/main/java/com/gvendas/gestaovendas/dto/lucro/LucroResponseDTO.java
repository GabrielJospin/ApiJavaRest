package com.gvendas.gestaovendas.dto.lucro;

import com.gvendas.gestaovendas.entidades.Lucro;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Lucro Retorno DTO")
public class LucroResponseDTO {

    @ApiModelProperty(value = "Código")
    private long codigo;

    @ApiModelProperty(value = "Lucro")
    private BigDecimal lucro;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Lucro Médio")
    private BigDecimal lucroMedio;

    @ApiModelProperty(value = "Codigo do Produto")
    private Long codigoProduto;

    @ApiModelProperty(value = "Codigo do Produto")
    private String nomeProduto;



    public LucroResponseDTO(long codigo, BigDecimal lucro, Integer quantidade, Long codigoProduto, String nomeProduto) {
        this.codigo = codigo;
        this.lucro = lucro;
        this.quantidade = quantidade;
        if(quantidade != 0) this.lucroMedio = BigDecimal.valueOf(lucro.floatValue() / quantidade.longValue())
                .setScale(2,BigDecimal.ROUND_HALF_EVEN);
        else this.lucroMedio = BigDecimal.valueOf(0);

        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
    }

    public static LucroResponseDTO converterParaDTO(Lucro lucro){
        return new LucroResponseDTO(lucro.getCodigo(),lucro.getLucro(),lucro.getQuantidade(),
                lucro.getProduto().getCodigo(),lucro.getProduto().getDescricao());
    }


    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        this.lucro = lucro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getLucroMedio() {
        return lucroMedio;
    }

    public void setLucroMedio(BigDecimal lucroMedio) {
        this.lucroMedio = lucroMedio;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
