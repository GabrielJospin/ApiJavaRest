package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.cliente.ClienteRequestDTO;
import com.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
import com.gvendas.gestaovendas.entidades.Cliente;
import com.gvendas.gestaovendas.servico.ClienteServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServico clienteServico;

    @ApiOperation(value = "listar", nickname = "listarTodosClientes")
    @GetMapping
    public List<ClienteResponseDTO> listarTodas(){
        return clienteServico.listarTodas().stream().map(ClienteResponseDTO::converterParaClienteDTO)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por Código", nickname = "BuscarClientePorId")
    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> buscarPorCodigo(@PathVariable Long codigo){
        Optional<Cliente> cliente = clienteServico.buscarPorCodigo(codigo);
        return cliente.isPresent()?
                ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO(cliente.get())):
                ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "salvar", nickname = "salvarClientes")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO cliente){
        Cliente clienteSalvo = clienteServico.salvar(cliente.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClienteResponseDTO.converterParaClienteDTO(clienteSalvo));
    }

    @ApiOperation(value = "atualizar", nickname = "AtualizarClientes")
    @PutMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long codigo,
                                                        @Valid @RequestBody ClienteRequestDTO cliente){
        Cliente clienteAtualizado = clienteServico.atualizar(codigo,cliente.converterParaEntidade(codigo));
        return ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO(clienteAtualizado));
    }

    @ApiOperation(value = "deletar", nickname = "Deletar clientes")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigo){
        clienteServico.delete(codigo);
    }




}
