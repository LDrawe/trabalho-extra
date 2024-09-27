package com.example.CalculoTaxaEntregaAPI.service;

import com.example.CalculoTaxaEntregaAPI.pedido.Pedido;
import com.example.CalculoTaxaEntregaAPI.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository PedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.PedidoRepository = pedidoRepository;
    }

    public Pedido criarPedido(Pedido pedido) {
        return PedidoRepository.salvar(pedido);
    }

    public List<Pedido> listarPedidos() {
        return PedidoRepository.buscarTodos();
    }
}