package com.example.CalculoTaxaEntregaAPI.pedido.moduloDescontoEntrega;

import com.example.CalculoTaxaEntregaAPI.pedido.Pedido;
import java.util.Map;

public interface IFormaCalculoDescontoEntrega {
    Map<String, Double> calcularDesconto(Pedido pedido);
    boolean seAplica(Pedido pedido);
}
