package com.example.CalculoTaxaEntregaAPI.service;

import com.example.CalculoTaxaEntregaAPI.pedido.Cliente;
import com.example.CalculoTaxaEntregaAPI.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = ClienteRepository.getInstance();
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.salvar(cliente);
    }

    public Cliente buscarCliente(Integer clientID) {
        return clienteRepository.buscarPorId(clientID);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.buscarTodos();
    }
}