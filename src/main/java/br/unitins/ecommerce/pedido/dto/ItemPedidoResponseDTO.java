package br.unitins.ecommerce.pedido.dto;

public record ItemPedidoResponseDTO(
    String nomeItem,
    Integer quantidade,
    Double precoUnitario,
    Double subtotal
) {}
