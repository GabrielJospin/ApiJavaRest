package com.gvendas.gestaovendas.dto.cliente;


import com.gvendas.gestaovendas.dto.endereco.EnderecoResponseDTO;
import com.gvendas.gestaovendas.entidades.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Cliente Retorno DTO")
public class ClienteResponseDTO {

    @ApiModelProperty(value = "Código")
    private Long codigo;

    @ApiModelProperty(value = "nome")
    private String nome;

    @ApiModelProperty(value = "telefone")
    private String telefone;

    @ApiModelProperty(value = "ativo")
    private Boolean ativo;

    private EnderecoResponseDTO enderecoDTO;

    public ClienteResponseDTO(Long codigo, String nome, String telefone, Boolean ativo,
                              EnderecoResponseDTO enderecoDTO) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.enderecoDTO = enderecoDTO;
    }

    public static ClienteResponseDTO converterParaClienteDTO(Cliente cliente){
        return new ClienteResponseDTO(cliente.getCodigo(),cliente.getNome(),cliente.getTelefone(),
                cliente.getAtivo(), EnderecoResponseDTO.converterParaClienteDTO(cliente.getEndereco()));
    }


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public EnderecoResponseDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoResponseDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }
}
