package com.gvendas.gestaovendas.dto.lucro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Lucro Métricas Retorno DTO")
public class LucroMetricsResponseDTO {

    @ApiModelProperty(value = "Lucro Total")
    private BigDecimal lucroTotal;

    @ApiModelProperty(value = "Quantidade Total")
    private Long quantidadeTotal;

    @ApiModelProperty(value = "lucro Máximo")
    private LucroResponseDTO lucroMaximo;

    @ApiModelProperty(value = "lucro Mínimo")
    private LucroResponseDTO lucroMinimo;

    public LucroMetricsResponseDTO(BigDecimal lucroTotal, Long quantidadeTotal, LucroResponseDTO lucroMaximo, LucroResponseDTO lucroMinimo) {
        this.lucroTotal = lucroTotal;
        this.quantidadeTotal = quantidadeTotal;
        this.lucroMaximo = lucroMaximo;
        this.lucroMinimo = lucroMinimo;
    }

    public BigDecimal getLucroTotal() {
        return lucroTotal;
    }

    public void setLucroTotal(BigDecimal lucroTotal) {
        this.lucroTotal = lucroTotal;
    }

    public Long getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Long quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public LucroResponseDTO getLucroMaximo() {
        return lucroMaximo;
    }

    public void setLucroMaximo(LucroResponseDTO lucroMaximo) {
        this.lucroMaximo = lucroMaximo;
    }

    public LucroResponseDTO getLucroMinimo() {
        return lucroMinimo;
    }

    public void setLucroMinimo(LucroResponseDTO lucroMinimo) {
        this.lucroMinimo = lucroMinimo;
    }
}
