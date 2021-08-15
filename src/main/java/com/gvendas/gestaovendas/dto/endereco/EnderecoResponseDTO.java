package com.gvendas.gestaovendas.dto.endereco;

import com.gvendas.gestaovendas.entidades.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Endereço retorno DTO")
public class EnderecoResponseDTO {

    @ApiModelProperty(value = "cep")
    private String cep;

    @ApiModelProperty(value = "logradouro")
    private String logradouro;

    @ApiModelProperty(value = "numero")
    private Long numero;

    @ApiModelProperty(value = "complemento")
    private String complemento;

    @ApiModelProperty(value = "bairro")
    private String bairro;

    @ApiModelProperty(value = "cidade")
    private String cidade;

    @ApiModelProperty(value = "estado")
    private String estado;

    public EnderecoResponseDTO(String cep, String logradouro, Long numero, String complemento,
                               String bairro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public static EnderecoResponseDTO converterParaClienteDTO(Endereco endereco){
        return new EnderecoResponseDTO(endereco.getCep(),endereco.getLogradouro(),endereco.getNumero(),
                endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado());
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
