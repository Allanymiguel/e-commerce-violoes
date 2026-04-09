package br.unitins.ecommerce.violao.service;

import java.util.List;

import br.unitins.ecommerce.violao.dto.ViolaoAcoRequestDTO;
import br.unitins.ecommerce.violao.dto.ViolaoAcoResponseDTO;

public interface ViolaoAcoService {
    
    List<ViolaoAcoResponseDTO> getAll();

    ViolaoAcoResponseDTO getById(Long id);

    // List<ViolaoAcoResponseDTO> getByName(String nome);

    // List<ViolaoAcoResponseDTO> getByMarca(Long id);

    void create(ViolaoAcoRequestDTO dto);

    void update(Long id, ViolaoAcoRequestDTO dto);

    void delete(Long id);
}