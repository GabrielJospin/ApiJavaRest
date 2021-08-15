package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import com.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.servico.LucroServico;
import com.gvendas.gestaovendas.servico.ProdutoServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags="Produto")
@RestController
@RequestMapping("/categoria/{codigoCategoria}/produto")
public class ProdutoControlador {

    @Autowired
    private ProdutoServico produtoServico;

    @Autowired
    private LucroServico lucroServico;

    @ApiOperation(value = "listar", nickname = "listarTodosProdutos")
    @GetMapping
    public List<ProdutoResponseDTO> listarTodas(@PathVariable Long codigoCategoria){
        return produtoServico.listarTodas(codigoCategoria)
                .stream().map(ProdutoResponseDTO::converterParaProdutoDTO)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "buscar por codigo", nickname = "buscarProdutoPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorCodigo(@PathVariable Long codigo, @PathVariable Long codigoCategoria){
        Optional<Produto> produto = produtoServico.buscarPorCodigo(codigo,codigoCategoria);
        return produto.isPresent() ?
                ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produto.get())) :
                ResponseEntity.notFound().build();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @ApiOperation(value = "salvar", nickname = "salvarProduto" )
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoRequestDTO produto){
        Produto produtoSalvar = produtoServico.salvar(codigoCategoria,produto.converterParaEntidade(codigoCategoria));
        lucroServico.adicionarProduto(produtoSalvar);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvar));
    }

    @ApiOperation(value = "atualizarProduto", nickname = "atualizarProduto")
    @PutMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long codigoCategoria,@PathVariable Long codigo,@Valid @RequestBody ProdutoRequestDTO produto){
        Produto produtoSalvar = produtoServico
                .atualizar(codigoCategoria,codigo,produto.converterParaEntidade(codigoCategoria,codigo));
        return ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvar));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @ApiOperation(value = "deleteProduto", nickname = "deleteProduto")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Delete(@PathVariable Long codigoCategoria,@PathVariable Long codigo){
        lucroServico.deletarProduto(codigo);
        produtoServico.delete(codigoCategoria, codigo);
    }


}
