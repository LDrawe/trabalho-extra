package com.example.CalculoTaxaEntregaAPI.repository;

import com.example.CalculoTaxaEntregaAPI.pedido.Pedido;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PedidoRepository {
    private static PedidoRepository repository;
    private final ArrayList<Pedido> bancoDeDados = new ArrayList<>();

    private PedidoRepository() {
    }

    public static PedidoRepository getInstance() {
        if (repository == null) {
            repository = new PedidoRepository();
        }
        return repository;
    }

    public Pedido salvar(Pedido pedido) {
        bancoDeDados.add(pedido);
        return pedido;
    }

    public List<Pedido> buscarTodos() {
        return bancoDeDados;
    }

    public Pedido buscarPorId(Integer id) {
        return bancoDeDados.get(id);
    }
}