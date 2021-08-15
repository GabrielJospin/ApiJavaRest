package com.gvendas.gestaovendas.servico;

import com.gvendas.gestaovendas.entidades.Cliente;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.ClienteRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listarTodas(){
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> buscarPorCodigo(Long codigo){
        return clienteRepositorio.findById(codigo);
    }

    public Cliente salvar(Cliente cliente){
        validarClienteDuplicado(cliente);
        return clienteRepositorio.save(cliente);
    }

    public Cliente atualizar(Long codigo, Cliente cliente){
        Cliente clienteSalvar = ValidarClienteExiste(codigo);
        validarClienteDuplicado(cliente);
        BeanUtils.copyProperties(cliente,clienteSalvar,"codigo");
        return clienteRepositorio.save(clienteSalvar);

    }

    public void delete(Long codigo){
        clienteRepositorio.deleteById(codigo);
    }


    private Cliente ValidarClienteExiste(Long codigo) {
        Optional<Cliente> cliente = buscarPorCodigo(codigo);

        if(!cliente.isPresent())
            throw new EmptyResultDataAccessException(1);

        return cliente.get();
    }


    private void validarClienteDuplicado(Cliente cliente) {
        Cliente clienteDoBanco = clienteRepositorio.findByNome(cliente.getNome());
        if(clienteDoBanco != null && clienteDoBanco.getCodigo() != cliente.getCodigo())
            throw new RegraNegocioException(
                    String.format("O cliente %s já está cadastrada",cliente.getNome().toUpperCase()));

    }

}
