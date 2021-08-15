package com.gvendas.gestaovendas.entidades;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {

    private static final int DESCRICAO_MIN_CHAR = 3;
    private static final int DESCRICAO_MAX_CHAR = 100;
    private static final int OBSERVACAO_MIN_CHAR = 0;
    private static final int OBSERVACAO_MAX_CHAR = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private long codigo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_curto")
    private BigDecimal precoCusto;

    @Column(name = "preco_venda")
    private BigDecimal precoVenda;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
    private Categoria categoria;

    public Produto(){
        
    }

    public Produto(long codigo) {
        this.codigo = codigo;
    }

    public Produto(String descricao, Integer quantidade, BigDecimal precoCusto, BigDecimal precoVenda,
                   String observacao, Categoria categoria) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }

    public Produto(Long codigoProduto, String descricao, Integer quantidade, BigDecimal precoCusto, BigDecimal precoVenda, String observacao, Categoria categoria) {
        this.codigo = codigoProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;


    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return codigo == produto.codigo &&
                Objects.equals(descricao, produto.descricao) &&
                Objects.equals(quantidade, produto.quantidade) &&
                Objects.equals(precoCusto, produto.precoCusto) &&
                Objects.equals(precoVenda, produto.precoVenda) &&
                Objects.equals(observacao, produto.observacao) &&
                Objects.equals(categoria, produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, descricao, quantidade, precoCusto, precoVenda, observacao, categoria);
    }
}
