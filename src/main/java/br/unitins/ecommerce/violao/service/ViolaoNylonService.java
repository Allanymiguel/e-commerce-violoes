package br.unitins.ecommerce.violao.service;

import java.util.List;

import br.unitins.ecommerce.violao.dto.ViolaoNylonRequestDTO;
import br.unitins.ecommerce.violao.dto.ViolaoNylonResponseDTO;

public interface ViolaoNylonService {
    
    List<ViolaoNylonResponseDTO> getAll();

    ViolaoNylonResponseDTO getById(Long id);

    // List<ViolaoAcoResponseDTO> getByName(String nome);

    // List<ViolaoAcoResponseDTO> getByMarca(Long id);

    void create(ViolaoNylonRequestDTO dto);

    boolean update(Long id, ViolaoNylonRequestDTO dto);

    boolean delete(Long id);
}