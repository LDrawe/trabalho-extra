package com.example.CalculoTaxaEntregaAPI.repository;

import com.example.CalculoTaxaEntregaAPI.pedido.Pedido;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PedidoRepository {

    private final Map<Long, Pedido> bancoDeDados = new HashMap<>();
    private long ultimoId = 0;

    public Pedido salvar(Pedido pedido) {
        ultimoId++;
        bancoDeDados.put(ultimoId, pedido);
        return pedido;
    }

    public List<Pedido> buscarTodos() {
        return new ArrayList<>(bancoDeDados.values());
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return Optional.ofNullable(bancoDeDados.get(id));
    }
}