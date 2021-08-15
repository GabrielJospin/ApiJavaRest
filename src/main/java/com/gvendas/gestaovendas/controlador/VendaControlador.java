package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import com.gvendas.gestaovendas.dto.venda.cliente.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.servico.VendaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags ="Venda")
@RestController
@RequestMapping("/venda")
public class VendaControlador {

    @Autowired
    private VendaServico vendaServico;

    @ApiOperation(value = "Listar Vendas por Cliente", nickname = "listarVendaPorCliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCleinte(@PathVariable Long codigoCliente){
        return ResponseEntity.ok(vendaServico.listaVendaPorCliente(codigoCliente));
    }

    @ApiOperation(value = "Listar Vendas por Codigo", nickname = "listarVendaPorCodigo")
    @GetMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(@PathVariable Long codigoVenda){
        return ResponseEntity.ok(vendaServico.listarVendaPorCodigo(codigoVenda));
    }

    @ApiOperation(value = "Registrar Venda", nickname = "salvar")
    @PostMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> salvar(@Valid @RequestBody VendaRequestDTO vendaDTO,
                                                          @PathVariable Long codigoCliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaServico.salvar(codigoCliente,vendaDTO));
    }

    @ApiOperation(value = "Atualizar Venda", nickname = "atualizar")
    @PutMapping("{codigoVenda}/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> atualizar(@Valid @RequestBody VendaRequestDTO vendaDTO,
                                                          @PathVariable Long codigoVenda ,@PathVariable Long codigoCliente){
        return ResponseEntity.ok(vendaServico.atualizar(codigoVenda,codigoCliente,vendaDTO));
    }

    @ApiOperation(value = "Deletar Venda", nickname = "delete")
    @DeleteMapping("/{codigoVenda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigoVenda){
        vendaServico.delete(codigoVenda);
    }

}
