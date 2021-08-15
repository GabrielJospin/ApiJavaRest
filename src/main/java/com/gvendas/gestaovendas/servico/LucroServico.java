package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.dto.lucro.LucroMetricsResponseDTO;
import com.gvendas.gestaovendas.dto.lucro.LucroResponseDTO;
import com.gvendas.gestaovendas.dto.venda.item.ItemVendaRequestDTO;
import com.gvendas.gestaovendas.entidades.ItemVenda;
import com.gvendas.gestaovendas.entidades.Lucro;
import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.LucroRepositorio;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LucroServico {

    LucroRepositorio lucroRepositorio;
    ProdutoServico produtoServico;

    public LucroServico(LucroRepositorio lucroRepositorio, ProdutoServico produtoServico) {
        this.lucroRepositorio = lucroRepositorio;
        this.produtoServico = produtoServico;
    }

    public List<Lucro> listarTodas(){
        return lucroRepositorio.findAll();
    }

    public Optional<Lucro> buscarPorCodigoProduto(Long codigoProduto) {
        List<Optional<Lucro>> list = lucroRepositorio.findByProdutoCodigo(codigoProduto);
        if(list.size() == 0)
            return Optional.empty();
        return  list.get(0);
    }

    public LucroMetricsResponseDTO metricsResponseDTOS(){
        List<Lucro> lucros = listarTodas();

        BigDecimal lucroTotal = BigDecimal.valueOf(0);
        long quantidade = 0L;
        int flagLucroMaximo = 0;
        int flagLucroMinimo = 0;

        for(int flagPosition = 0; flagPosition<lucros.size(); flagPosition++){
            lucroTotal = lucroTotal.add(lucros.get(flagPosition).getLucro());
            quantidade += lucros.get(flagPosition).getQuantidade();

            if(lucros.get(flagPosition).getLucro().floatValue() > lucros.get(flagLucroMaximo).getLucro().floatValue())
                flagLucroMaximo = flagPosition;

            if(lucros.get(flagPosition).getLucro().floatValue() < lucros.get(flagLucroMinimo).getLucro().floatValue())
                flagLucroMinimo = flagPosition;
        }

        return new LucroMetricsResponseDTO(lucroTotal,quantidade,
                LucroResponseDTO.converterParaDTO(lucros.get(flagLucroMaximo)),
                LucroResponseDTO.converterParaDTO(lucros.get(flagLucroMinimo)));


    }

    public void adicionarLucroVenda(ItemVendaRequestDTO venda){
        Long codigoProduto = venda.getCodigoProduto();
        Produto produto =  produtoServico.validarProdutoExiste(codigoProduto);
        Optional<Lucro> lucroOptional = buscarPorCodigoProduto(codigoProduto);

        if(!(lucroOptional.isPresent()))
            throw new RegraNegocioException(
                    String.format("Lucro do produto %s não encontrado na tabela lucro",produto.getDescricao()));

        Lucro lucroAntigo = lucroOptional.get();

        BigDecimal valorLucroTemporario = lucroAntigo.getLucro();
        BigDecimal valorLucroVenda = venda.getPrecoVendido().subtract(produto.getPrecoCusto());
        Integer quantidade = lucroAntigo.getQuantidade() + venda.getQuantidade();

        BigDecimal valorLucroFinal = valorLucroTemporario.add(valorLucroVenda);

        atualizarLucros(lucroAntigo,valorLucroFinal,quantidade,produto);
    }

    public void removerLucroVenda(ItemVenda venda){
        Long codigoProduto = venda.getProduto().getCodigo();
        Produto produto =  produtoServico.validarProdutoExiste(codigoProduto);
        Optional<Lucro> lucroOptional = buscarPorCodigoProduto(codigoProduto);

        if(!(lucroOptional.isPresent()))
            throw new RegraNegocioException(
                    String.format("Lucro do produto %s não encontrado na tabela lucro",produto.getDescricao()));

        Lucro lucroAntigo = lucroOptional.get();

        BigDecimal valorLucroTemporario = lucroAntigo.getLucro();
        BigDecimal valorLucroVenda = venda.getPrecoVendido().subtract(produto.getPrecoCusto());
        Integer quantidade = lucroAntigo.getQuantidade() - venda.getQuantidade();
        BigDecimal valorLucroFinal = valorLucroTemporario.subtract(valorLucroVenda);

        atualizarLucros(lucroAntigo,valorLucroFinal,quantidade,produto);
    }

    private void atualizarLucros(Lucro lucro, BigDecimal valorLucro, Integer quantidade, Produto produto){

        lucro.setLucro(valorLucro);
        lucro.setQuantidade(quantidade);
        lucro.setProduto(produto);

        lucroRepositorio.save(lucro);

    }


    public void adicionarProduto(Produto produto) {
        Lucro lucro = new Lucro(BigDecimal.ZERO,0,produto);
        lucroRepositorio.save(lucro);
    }

    public void deletarProduto(Long codigoProduto) {

        Optional<Lucro> lucro = buscarPorCodigoProduto(codigoProduto);
        if(lucro.isPresent())
            lucroRepositorio.delete(lucro.get());

    }
}
