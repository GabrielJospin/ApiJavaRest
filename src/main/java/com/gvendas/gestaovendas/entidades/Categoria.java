package com.gvendas.gestaovendas.entidades;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria")
public class Categoria {

    public static final int  MIN_CHAR = 3;
    public static final int  MAX_CHAR = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private long codigo;

    private String nome;

    public Categoria() {

    }

    public Categoria(long codigo) {
        this.codigo = codigo;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return codigo == categoria.codigo &&
                Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome);
    }
}
