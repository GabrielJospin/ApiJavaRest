package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.ProdutoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaServico categoriaServico;

    public List<Produto> listarTodas(Long codigoCategoria){
        return produtoRepositorio.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria){
        return produtoRepositorio.buscarPorCodigo(codigo,codigoCategoria);
    }

    public Produto salvar(Long codigoCategoria, Produto produto){
        validarCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoExiste(produto);
        Produto resposta = produtoRepositorio.save(produto);
        return resposta;
    }

    public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto){
        Produto produtoSalvar = validarProdutoExiste(codigoProduto,codigoCategoria);
        validarCategoriaDoProdutoExiste(produto.getCategoria().getCodigo());
        validarProdutoExiste(produto);
        BeanUtils.copyProperties(produto,produtoSalvar,"codigo");
        return produtoRepositorio.save(produtoSalvar);
    }

    public void delete(Long codigoCategoria, Long codigoProduto){
        validarProdutoExiste(codigoProduto,codigoCategoria);
        produtoRepositorio.deleteById(codigoProduto);
    }

    protected void atualizarProdutoEstoque(Produto produto){
        produtoRepositorio.save(produto);
    }


    private Produto validarProdutoExiste(Long codigoProduto, Long codigoCategoria) {
       Optional<Produto> produto = buscarPorCodigo(codigoProduto,codigoCategoria);
        if(!produto.isPresent())
            throw new EmptyResultDataAccessException(1);

        return produto.get();
    }

    protected Produto validarProdutoExiste(Long codigoProduto) {
        Optional<Produto> produto = produtoRepositorio.findById(codigoProduto);
        if(!produto.isPresent())
            throw new EmptyResultDataAccessException(1);

        return produto.get();
    }




    private void validarCategoriaDoProdutoExiste(Long codigoCategoria){
        if(codigoCategoria == null)
            throw new RegraNegocioException("A categoria não pode ser nula");

        if(!categoriaServico.buscarPorCodigo(codigoCategoria).isPresent())
            throw new RegraNegocioException(String.format("A categoria %s informada Não existe",codigoCategoria));

    }

    private void validarProdutoExiste(Produto produto) {
        Optional<Produto> produtoBanco = produtoRepositorio.findByCategoriaCodigoAndDescricao(
                produto.getCategoria().getCodigo(),
                produto.getDescricao());



        if(produtoBanco.isPresent() && produtoBanco.get().getCodigo() != produto.getCodigo())
            throw new RegraNegocioException(String.format("O produto %s  já existe na categoria %s",
                    produto.getDescricao().toUpperCase(),
                    produto.getCategoria().getNome().toUpperCase()));

    }

}
