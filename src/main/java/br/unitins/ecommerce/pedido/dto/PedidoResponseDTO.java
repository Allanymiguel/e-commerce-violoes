package br.unitins.ecommerce.pedido.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.ecommerce.pedido.model.StatusPedido;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime dataPedido,
    StatusPedido status,
    Double total,
    List<ItemPedidoResponseDTO> itens
) {}
