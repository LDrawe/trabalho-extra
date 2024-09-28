package com.example.CalculoTaxaEntregaAPI.DTO;

import com.example.CalculoTaxaEntregaAPI.pedido.Item;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO(LocalDate data, Integer clienteId, List<Item> itens) {
}