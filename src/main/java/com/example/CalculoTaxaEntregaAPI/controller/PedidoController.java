package com.example.CalculoTaxaEntregaAPI.controller;

import com.example.CalculoTaxaEntregaAPI.DTO.PedidoDTO;
import com.example.CalculoTaxaEntregaAPI.pedido.Cliente;
import com.example.CalculoTaxaEntregaAPI.pedido.Pedido;
import com.example.CalculoTaxaEntregaAPI.repository.ClienteRepository;
import com.example.CalculoTaxaEntregaAPI.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Cliente cliente = ClienteRepository.getInstance().buscarPorId(pedidoDTO.clienteId());
        if (cliente == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle not found case

        pedidoService.criarPedido(new Pedido(pedidoDTO.data(), cliente));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
