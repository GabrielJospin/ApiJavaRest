package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.dto.endereco.EnderecoRequestDTO;
import com.gvendas.gestaovendas.entidades.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("Cliente Requisição DTO")
public class ClienteRequestDTO {

    public static final int  MIN_CHAR = 3;
    public static final int  MAX_CHAR_NOME = 50;
    public static final String REGEXP_TELEFONE = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}";


    @ApiModelProperty(value = "nome")
    @NotBlank(message = "Nome")
    @Length(min=MIN_CHAR,max=MAX_CHAR_NOME,message = "Nome")
    private String nome;

    @ApiModelProperty(value = "telefone")
    @NotBlank(message = "telefone")
    @Pattern(regexp = REGEXP_TELEFONE, message = "telefone")
    private String telefone;

    @ApiModelProperty(value = "ativo")
    @NotNull(message = "ativo")
    private Boolean ativo;

    private EnderecoRequestDTO enderecoDTO;


    public Cliente converterParaEntidade() {
        return new Cliente(this.nome, this.telefone, this.ativo, this.enderecoDTO.converterParaEntidade());
    }

    public Cliente converterParaEntidade(Long codigo) {
        return new Cliente(codigo ,this.nome, this.telefone, this.ativo, this.enderecoDTO.converterParaEntidade());
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public EnderecoRequestDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoRequestDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }



}
