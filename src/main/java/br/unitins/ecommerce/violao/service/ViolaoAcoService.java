package br.unitins.ecommerce.violao.service;

import java.util.List;

import br.unitins.ecommerce.violao.dto.ViolaoAcoRequestDTO;
import br.unitins.ecommerce.violao.dto.ViolaoAcoResponseDTO;

public interface ViolaoAcoService {
    
    List<ViolaoAcoResponseDTO> getAll();

    ViolaoAcoResponseDTO getById(Long id);

    void insert(ViolaoAcoRequestDTO dto);

    void update(Long id, ViolaoAcoRequestDTO dto);

    void delete(Long id);
}