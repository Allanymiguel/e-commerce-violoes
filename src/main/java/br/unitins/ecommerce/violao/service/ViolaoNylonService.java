package br.unitins.ecommerce.violao.service;

import java.util.List;

import br.unitins.ecommerce.violao.dto.ViolaoNylonRequestDTO;
import br.unitins.ecommerce.violao.dto.ViolaoNylonResponseDTO;

public interface ViolaoNylonService {
    
    List<ViolaoNylonResponseDTO> getAll();

    ViolaoNylonResponseDTO getById(Long id);

    void insert(ViolaoNylonRequestDTO dto);

    void update(Long id, ViolaoNylonRequestDTO dto);

    void delete(Long id);
}