package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.lucro.LucroMetricsResponseDTO;
import com.gvendas.gestaovendas.dto.lucro.LucroResponseDTO;
import com.gvendas.gestaovendas.entidades.Lucro;
import com.gvendas.gestaovendas.servico.LucroServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags="Lucro")
@RestController
@RequestMapping("/lucro")
public class LucroControlador {

    @Autowired
    private LucroServico lucroServico;

    @ApiOperation(value = "listar lucro", nickname = "listarTodas")
    @GetMapping
    public List<LucroResponseDTO> listarTodas(){
        return lucroServico.listarTodas().stream()
                .map(LucroResponseDTO::converterParaDTO).collect(Collectors.toList());
    }

    @ApiOperation(value = "lucro do Produto", nickname = "listarPorProduto")
    @GetMapping("/{codigoProduto}")
    public ResponseEntity<LucroResponseDTO> listarPorProduto(@PathVariable Long codigoProduto){
        Optional<Lucro> lucro = lucroServico.buscarPorCodigoProduto(codigoProduto);
        return lucro.isPresent()?
                ResponseEntity.ok(LucroResponseDTO.converterParaDTO(lucro.get())):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @ApiOperation(value = "Métricas do Lucro", nickname = "obterMetricas")
    @GetMapping("/Metrics")
    public ResponseEntity<LucroMetricsResponseDTO> obterMetricas(){
        LucroMetricsResponseDTO lucroMetrics = lucroServico.metricsResponseDTOS();
        return ResponseEntity.ok(lucroMetrics);
    }



}
