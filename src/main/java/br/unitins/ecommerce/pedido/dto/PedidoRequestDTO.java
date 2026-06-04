package br.unitins.ecommerce.pedido.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record PedidoRequestDTO(

    @NotEmpty(message = "O pedido deve ter ao menos um item")
    List<@Valid ItemPedidoRequestDTO> itens,

    Long idEndereco
) {}
