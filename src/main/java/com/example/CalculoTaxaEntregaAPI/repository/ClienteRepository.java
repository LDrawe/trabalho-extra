package com.example.CalculoTaxaEntregaAPI.repository;

import com.example.CalculoTaxaEntregaAPI.pedido.Cliente;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ClienteRepository {
    private static ClienteRepository repository;
    private final ArrayList<Cliente> bancoDeDados = new ArrayList<>();

    private ClienteRepository() {
    }

    public static ClienteRepository getInstance() {
        if (repository == null) {
            repository = new ClienteRepository();
        }
        return repository;
    }

    public Cliente salvar(Cliente cliente) {
        cliente.setId(bancoDeDados.size());
        bancoDeDados.add(cliente);
        return cliente;
    }

    public List<Cliente> buscarTodos() {
        return bancoDeDados;
    }

    public Cliente buscarPorId(Integer id) {
        if (id > bancoDeDados.size() || bancoDeDados.size() == 0 || id < 0) {
            return null;
        }

        return bancoDeDados.get(id);
    }
}