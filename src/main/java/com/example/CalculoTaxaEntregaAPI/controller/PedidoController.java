package com.example.CalculoTaxaEntregaAPI.controller;

import com.example.CalculoTaxaEntregaAPI.DTO.PedidoDTO;
import com.example.CalculoTaxaEntregaAPI.pedido.CalculadoraDescontoService;
import com.example.CalculoTaxaEntregaAPI.pedido.Cliente;
import com.example.CalculoTaxaEntregaAPI.pedido.Item;
import com.example.CalculoTaxaEntregaAPI.pedido.Pedido;
import com.example.CalculoTaxaEntregaAPI.repository.ClienteRepository;
import com.example.CalculoTaxaEntregaAPI.repository.PedidoRepository;
import com.example.CalculoTaxaEntregaAPI.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Pedido pedido = new Pedido(pedidoDTO.data(), cliente);

        for (Item item : pedidoDTO.itens()) {
            pedido.adicionarItem(item);
        }

        pedidoService.criarPedido(pedido);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{pedidoID}")
    public ResponseEntity<Pedido> getDescontos(@PathVariable Integer pedidoID) {
        Pedido pedido = PedidoRepository.getInstance().buscarPorId(pedidoID);

        if (pedido == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle not found case

        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PostMapping("/{pedidoID}/processar-descontos")
    public ResponseEntity<Pedido> processarDescontos(@PathVariable Integer pedidoID) {
        Pedido pedido = PedidoRepository.getInstance().buscarPorId(pedidoID);

        if (pedido == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle not found case
        CalculadoraDescontoService calculadoraDescontoService = new CalculadoraDescontoService();
        calculadoraDescontoService.processar(pedido);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
