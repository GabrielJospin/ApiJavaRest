package com.gvendas.gestaovendas.entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "lucro")
public class Lucro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private long codigo;

    @Column(name = "lucro_obtido")
    private BigDecimal lucro;

    @Column(name = "quantidade_vendida")
    private Integer quantidade;

    @OneToOne
    @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo")
    private Produto produto;

    public Lucro() {
    }

    public Lucro(BigDecimal lucro, Integer quantidade, Produto produto) {
        this.lucro = lucro;
        this.quantidade = quantidade;
        this.produto = produto;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lucro)) return false;
        Lucro lucro1 = (Lucro) o;
        return codigo == lucro1.codigo &&
                Objects.equals(lucro, lucro1.lucro) &&
                Objects.equals(quantidade, lucro1.quantidade) &&
                Objects.equals(produto, lucro1.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, lucro, quantidade, produto);
    }
}
