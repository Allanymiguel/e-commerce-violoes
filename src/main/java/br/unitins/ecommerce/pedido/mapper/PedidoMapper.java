package br.unitins.ecommerce.pedido.mapper;

import java.util.List;

import br.unitins.ecommerce.pedido.dto.ItemPedidoResponseDTO;
import br.unitins.ecommerce.pedido.dto.PedidoResponseDTO;
import br.unitins.ecommerce.pedido.model.ItemPedido;
import br.unitins.ecommerce.pedido.model.Pedido;

public class PedidoMapper {

    public static PedidoResponseDTO toResponseDTO(Pedido pedido) {
        List<ItemPedidoResponseDTO> itens = pedido.getItens().stream()
                .map(PedidoMapper::toItemResponseDTO)
                .toList();

        double total = itens.stream()
                .mapToDouble(ItemPedidoResponseDTO::subtotal)
                .sum();

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                total,
                itens
        );
    }

    private static ItemPedidoResponseDTO toItemResponseDTO(ItemPedido item) {
        String nomeItem = item.getViolao() != null
                ? item.getViolao().getNome()
                : item.getAcessorio().getNome();

        double subtotal = item.getPrecoUnitario() * item.getQuantidade();

        return new ItemPedidoResponseDTO(
                nomeItem,
                item.getQuantidade(),
                item.getPrecoUnitario(),
                subtotal
        );
    }
}
