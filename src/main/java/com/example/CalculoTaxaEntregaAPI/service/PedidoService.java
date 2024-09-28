package com.example.CalculoTaxaEntregaAPI.service;

import com.example.CalculoTaxaEntregaAPI.pedido.Pedido;
import com.example.CalculoTaxaEntregaAPI.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService() {
        this.pedidoRepository = PedidoRepository.getInstance();
    }

    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepository.salvar(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.buscarTodos();
    }
}