package br.unitins.ecommerce.endereco.service;

import java.util.List;

import br.unitins.ecommerce.endereco.dto.EnderecoRequestDTO;
import br.unitins.ecommerce.endereco.dto.EnderecoResponseDTO;

public interface EnderecoService {
    List<EnderecoResponseDTO> findByKeycloakId(String keycloakId);
    void create(String keycloakId, EnderecoRequestDTO dto);
    void update(Long id, String keycloakId, EnderecoRequestDTO dto);
    void delete(Long id, String keycloakId);
}
