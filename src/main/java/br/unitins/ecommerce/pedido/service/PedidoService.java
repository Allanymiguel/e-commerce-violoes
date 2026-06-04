package br.unitins.ecommerce.pedido.service;

import java.util.List;

import br.unitins.ecommerce.pedido.dto.PedidoRequestDTO;
import br.unitins.ecommerce.pedido.dto.PedidoResponseDTO;

public interface PedidoService {
    PedidoResponseDTO create(String keycloakId, PedidoRequestDTO dto);
    List<PedidoResponseDTO> findByKeycloakId(String keycloakId);
    PedidoResponseDTO findByIdAndKeycloakId(Long id, String keycloakId);
}
