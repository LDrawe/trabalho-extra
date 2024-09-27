package com.example.CalculoTaxaEntregaAPI.controller;

import com.example.CalculoTaxaEntregaAPI.pedido.Cliente;
import com.example.CalculoTaxaEntregaAPI.service.ClienteService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        List<Cliente> lista = clienteService.listarClientes();
        // Using a for loop to print each element
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }
}
