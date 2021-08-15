package com.gvendas.gestaovendas.dto.venda.cliente;


import com.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel("Cliente da Venda Response DTO")
public class ClienteVendaResponseDTO {

    @ApiModelProperty(value = "Nome cliente")
    private String nome;

    @ApiModelProperty(value = "vendas do cliente")
    private List<VendaResponseDTO> vendaResponseDTO;

    public ClienteVendaResponseDTO(String nome, List<VendaResponseDTO> vendaResponseDTO) {
        this.nome = nome;
        this.vendaResponseDTO = vendaResponseDTO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<VendaResponseDTO> getVendaResponseDTO() {
        return vendaResponseDTO;
    }

    public void setVendaResponseDTO(List<VendaResponseDTO> vendaResponseDTO) {
        this.vendaResponseDTO = vendaResponseDTO;
    }
}
