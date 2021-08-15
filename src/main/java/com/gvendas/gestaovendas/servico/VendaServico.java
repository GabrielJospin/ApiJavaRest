package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import com.gvendas.gestaovendas.dto.venda.cliente.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.item.ItemVendaRequestDTO;
import com.gvendas.gestaovendas.entidades.Cliente;
import com.gvendas.gestaovendas.entidades.ItemVenda;
import com.gvendas.gestaovendas.entidades.Produto;
import com.gvendas.gestaovendas.entidades.Venda;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.ItemVendaRepositorio;
import com.gvendas.gestaovendas.repositorio.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VendaServico extends AbstractVendaServico{


    private VendaRepositorio vendaRepositorio;
    private ItemVendaRepositorio itemVendaRepositorio;
    private ClienteServico clienteServico;
    private ProdutoServico produtoServico;
    private LucroServico lucroServico;

    @Autowired
    public VendaServico(VendaRepositorio vendaRepositorio, ItemVendaRepositorio itemVendaRepositorio,
                        ClienteServico clienteServico, ProdutoServico produtoServico,
                        LucroServico lucroServico) {
        this.vendaRepositorio = vendaRepositorio;
        this.itemVendaRepositorio = itemVendaRepositorio;
        this.clienteServico = clienteServico;
        this.produtoServico = produtoServico;
        this.lucroServico = lucroServico;
    }


    public ClienteVendaResponseDTO listaVendaPorCliente(Long codigoCliente){
        Cliente cliente = validarClienteVendaExiste(codigoCliente);

        List<VendaResponseDTO> vendaResponseDTO = vendaRepositorio.findByClienteCodigo(codigoCliente)
                .stream().map(venda->criandoVendaRespondeDTO(venda,itemVendaRepositorio.findByVendaPorCodigo(venda.getCodigo()))).collect(Collectors.toList());

        return new ClienteVendaResponseDTO(cliente.getNome(),vendaResponseDTO);

    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda){
        Venda venda = validarVendaExiste(codigoVenda);
        List<ItemVenda> itensVenda = itemVendaRepositorio.findByVendaPorCodigo(venda.getCodigo());

        return criandoClienteVendaResponseDTO(venda, itensVenda);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public ClienteVendaResponseDTO salvar(Long codigoCliente, VendaRequestDTO vendaDTO){
        Cliente cliente = validarClienteVendaExiste(codigoCliente);
        validarProdutoExisteERetirarEstoque(vendaDTO.getItensVendaDTO());
        Venda venda = salvarVenda(cliente,vendaDTO);
        List<ItemVenda> itensVenda = itemVendaRepositorio.findByVendaPorCodigo(venda.getCodigo());

        return criandoClienteVendaResponseDTO(venda, itensVenda);
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public ClienteVendaResponseDTO atualizar(Long codigoVenda,Long codigoCliente, VendaRequestDTO vendaDTO){
        validarVendaExiste(codigoVenda);
        Cliente cliente = validarClienteVendaExiste(codigoCliente);
        List<ItemVenda> itensVenda = itemVendaRepositorio.findByVendaPorCodigo(codigoVenda);
        validarProdutoExisteEDevolverEstoque(itensVenda);
        validarProdutoExisteERetirarEstoque(vendaDTO.getItensVendaDTO());
        itemVendaRepositorio.deleteAll(itensVenda);
        Venda vendaAtualizada =  atualizarVenda(codigoVenda,cliente,vendaDTO);
        List<ItemVenda> itensVendaAtualizados = itemVendaRepositorio.findByVendaPorCodigo(codigoVenda);

        return criandoClienteVendaResponseDTO(vendaAtualizada, itensVendaAtualizados);
    }



    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void delete(Long codigoVenda){
        Venda venda = validarVendaExiste(codigoVenda);
        List<ItemVenda> itensVenda = itemVendaRepositorio.findByVendaPorCodigo(codigoVenda);
        validarProdutoExisteEDevolverEstoque(itensVenda);
        itemVendaRepositorio.deleteAll(itensVenda);
        vendaRepositorio.delete(venda);
    }


    private Venda salvarVenda(Cliente cliente, VendaRequestDTO vendaDTO){

        Venda venda =  vendaRepositorio.save(new Venda(vendaDTO.getData(),cliente));
        vendaDTO.getItensVendaDTO().stream().map(item-> criandoItemVenda(item,venda))
                .forEach(itemVendaRepositorio::save);
        return venda;
    }

    private Venda atualizarVenda(Long codigoVenda ,Cliente cliente, VendaRequestDTO vendaDTO){

        Venda venda =  vendaRepositorio.save(new Venda(codigoVenda,vendaDTO.getData(),cliente));
        vendaDTO.getItensVendaDTO().stream().map(item-> criandoItemVenda(item,venda))
                .forEach(itemVendaRepositorio::save);
        return venda;
    }

    private void validarProdutoExisteERetirarEstoque(List<ItemVendaRequestDTO> itensVendaDTO) {
        itensVendaDTO.forEach(item -> {
            Produto produto = produtoServico.validarProdutoExiste(item.getCodigoProduto());
            validarQuantidadeProdutoExiste(produto,item.getQuantidade());
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoServico.atualizarProdutoEstoque(produto);
            lucroServico.adicionarLucroVenda(item);
        });
    }

    private void validarProdutoExisteEDevolverEstoque(List<ItemVenda> itensVenda) {
        itensVenda.forEach(item -> {
            Produto produto = produtoServico.validarProdutoExiste(item.getProduto().getCodigo());
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            produtoServico.atualizarProdutoEstoque(produto);
            lucroServico.removerLucroVenda(item);
        });
    }

    private void validarQuantidadeProdutoExiste(Produto produto, Integer qtdVendaDTO){
        if(produto.getQuantidade() < qtdVendaDTO)
            throw new RegraNegocioException(String
                    .format("A quantidade de %s informada para o produto %s não está disponivel em estoque (%s)",
                            qtdVendaDTO,produto.getDescricao(),produto.getQuantidade()));

    }


    private Cliente validarClienteVendaExiste(Long codigoCliente) {
       Optional<Cliente> cliente = clienteServico.buscarPorCodigo(codigoCliente);
        if(!cliente.isPresent())
            throw new RegraNegocioException(String.format("O Cliente de Código %d não existe",codigoCliente));
        return cliente.get();
    }

    private Venda validarVendaExiste(Long codigoVenda) {
        Optional<Venda> venda = vendaRepositorio.findById(codigoVenda);
        if(!venda.isPresent())
            throw new RegraNegocioException(String.format("A Venda de código %d não Existe", codigoVenda));
        return venda.get();
    }


}
