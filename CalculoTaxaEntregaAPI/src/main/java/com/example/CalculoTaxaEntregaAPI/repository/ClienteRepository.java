package com.example.CalculoTaxaEntregaAPI.repository;

import com.example.CalculoTaxaEntregaAPI.pedido.Cliente;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ClienteRepository {

    private final Map<Long, Cliente> bancoDeDados = new HashMap<>();
    private long ultimoId = 0;

    public Cliente salvar(Cliente cliente) {
        ultimoId++;
        bancoDeDados.put(ultimoId, cliente);
        return cliente;
    }

    public List<Cliente> buscarTodos() {
        return new ArrayList<>(bancoDeDados.values());
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return Optional.ofNullable(bancoDeDados.get(id));
    }
}