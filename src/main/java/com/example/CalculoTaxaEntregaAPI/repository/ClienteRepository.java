package com.example.CalculoTaxaEntregaAPI.repository;

import com.example.CalculoTaxaEntregaAPI.pedido.Cliente;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ClienteRepository {

    private final ArrayList<Cliente> bancoDeDados = new ArrayList<>();

    public Cliente salvar(Cliente cliente) {
        bancoDeDados.add(cliente);
        return cliente;
    }

    public List<Cliente> buscarTodos() {
        return bancoDeDados;
    }

    public Optional<Cliente> buscarPorId(Integer id) {
        return Optional.ofNullable(bancoDeDados.get(id));
    }
}