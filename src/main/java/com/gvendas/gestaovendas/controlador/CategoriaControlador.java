package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.categoria.*;
import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.servico.CategoriaServico;
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

@Api(tags="Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @ApiOperation(value = "listar", nickname = "listarTodasCategorias")
    @GetMapping
    public List<CategoriaResponseDTO> listarTodas(){
        return categoriaServico.listarTodas().stream()
                .map(CategoriaResponseDTO::converterParaCategoriaDTO)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "listar por codigo", nickname = "buscarCategoriaPorId")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long codigo){
        Optional<Categoria> categoria =  categoriaServico.buscarPorCodigo(codigo);
        return categoria.isPresent() ?
                ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoria.get())) :
                ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "salvar", nickname = "salvarCategorias")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoria){
        Categoria categoriaSalva = categoriaServico.salvar(categoria.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaSalva));
    }

    @ApiOperation(value = "atualizar", nickname = "atualizarCategorias")
    @PutMapping("/{codigo}")
    public  ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long codigo,
                                                           @Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        Categoria categoriaAtualizada = categoriaServico.atualizar(codigo,
                categoriaRequestDTO.converterParaEntidade(codigo));
        return  ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaAtualizada));
    }

    @ApiOperation(value = "delete", nickname = "deleteCategorias")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo){
        categoriaServico.delete(codigo);
    }

}
