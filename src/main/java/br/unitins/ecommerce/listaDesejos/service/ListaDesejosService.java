package br.unitins.ecommerce.listaDesejos.service;

import java.util.List;

import br.unitins.ecommerce.listaDesejos.dto.ListaDesejosRequestDTO;
import br.unitins.ecommerce.listaDesejos.dto.ListaDesejosResponseDTO;

public interface ListaDesejosService {
    List<ListaDesejosResponseDTO> findByKeycloakId(String keycloakId);
    void create(String keycloakId, ListaDesejosRequestDTO dto);
    void delete(Long id, String keycloakId);
}
