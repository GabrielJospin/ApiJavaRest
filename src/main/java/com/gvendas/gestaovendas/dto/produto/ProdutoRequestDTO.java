package com.gvendas.gestaovendas.dto.produto;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.entidades.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Produto Requição DTO")
public class ProdutoRequestDTO {
    private static final int DESCRICAO_MIN_CHAR = 3;
    private static final int DESCRICAO_MAX_CHAR = 100;
    private static final int OBSERVACAO_MIN_CHAR = 0;
    private static final int OBSERVACAO_MAX_CHAR = 500;

    @NotBlank(message = "Descrição")
    @Length(min=DESCRICAO_MIN_CHAR,max=DESCRICAO_MAX_CHAR,message = "Descrição")
    @ApiModelProperty("Descrição")
    private String descricao;

    @NotNull(message = "quantidade")
    @ApiModelProperty("Quantidade")
    private Integer quantidade;

    @NotNull(message = "preço Custo")
    @ApiModelProperty("Preço Custo")
    private BigDecimal precoCusto;

    @NotNull(message = "preço Venda")
    @ApiModelProperty("Preço Venda")
    private BigDecimal precoVenda;

    @Length(min = OBSERVACAO_MIN_CHAR,max=OBSERVACAO_MAX_CHAR)
    @ApiModelProperty("Observação")
    private String observacao;

    public Produto converterParaEntidade(Long codigoCategoria){
        return new Produto(this.descricao,this.quantidade,
                this.precoCusto,this.precoVenda,this.observacao,new Categoria(codigoCategoria));
    }

    public Produto converterParaEntidade(Long codigoCategoria,Long codigoProduto){
        return new Produto(codigoProduto,this.descricao,this.quantidade,
                this.precoCusto,this.precoVenda,this.observacao,new Categoria(codigoCategoria));
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
