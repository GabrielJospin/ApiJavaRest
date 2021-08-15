package com.gvendas.gestaovendas.dto.endereco;

import com.gvendas.gestaovendas.entidades.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@ApiModel("Endereço requisição DTO")
public class EnderecoRequestDTO {

    public static final int  MAX_CHAR = 30;
    public static final String  REGEXP_CEP = "[\\d]{5}-[\\d]{3}";


    @ApiModelProperty(value = "cep")
    @Pattern(regexp = REGEXP_CEP, message="cep")
    private String cep;

    @ApiModelProperty(value = "logradouro")
    @Length(max = MAX_CHAR , message="logradouro")
    private String logradouro;

    @ApiModelProperty(value = "numero")
    private Long numero;

    @ApiModelProperty(value = "complemento")
    @Length(max = MAX_CHAR , message="complemento")
    private String complemento;

    @ApiModelProperty(value = "bairro")
    @Length(max = MAX_CHAR , message="bairro")
    private String bairro;

    @ApiModelProperty(value = "cidade")
    @Length(max = MAX_CHAR,  message="cidade")
    private String cidade;

    @ApiModelProperty(value = "estado")
    @Length(max = MAX_CHAR, message="estado")
    private String estado;


    public Endereco converterParaEntidade() {
        return new Endereco(this.cep,this.logradouro,this.numero,
                this.complemento, this.bairro, this.cidade, this.estado);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
