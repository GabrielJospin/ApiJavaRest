package com.gvendas.gestaovendas.repositorio;

import com.gvendas.gestaovendas.entidades.Lucro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface LucroRepositorio extends JpaRepository<Lucro,Long>{

    @Query("select lucr from Lucro lucr" +
            " where lucr.produto.codigo = :codigoProduto")
    List<Optional<Lucro>> findByProdutoCodigo(Long codigoProduto);

}
