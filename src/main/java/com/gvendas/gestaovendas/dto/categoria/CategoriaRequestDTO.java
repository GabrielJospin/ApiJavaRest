package com.gvendas.gestaovendas.dto.categoria;

import com.gvendas.gestaovendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@ApiModel("Catgoria Requisição DTO")
public class CategoriaRequestDTO {

    public static final int  MIN_CHAR = 3;
    public static final int  MAX_CHAR = 50;

    @ApiModelProperty(value = "nome")
    @NotBlank(message = "Nome")
    @Length(min=MIN_CHAR,max=MAX_CHAR,message = "Nome")
    private String nome;

    public Categoria converterParaEntidade(){
        return new Categoria(this.nome);
    }

    public Categoria converterParaEntidade(Long codigo){
        return new Categoria(codigo, this.nome);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
