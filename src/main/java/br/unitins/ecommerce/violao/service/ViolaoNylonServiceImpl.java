package br.unitins.ecommerce.violao.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.ecommerce.violao.dto.ViolaoNylonRequestDTO;
import br.unitins.ecommerce.violao.dto.ViolaoNylonResponseDTO;
import br.unitins.ecommerce.violao.mapper.ViolaoNylonMapper;
import br.unitins.ecommerce.violao.model.ViolaoNylon;
import br.unitins.ecommerce.violao.repository.ViolaoNylonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ViolaoNylonServiceImpl implements ViolaoNylonService {

    @Inject
    private ViolaoNylonRepository violaoNylonRepository;

    @Override
    public List<ViolaoNylonResponseDTO> getAll() {
        return violaoNylonRepository.listAll().stream()
                .map(ViolaoNylonMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ViolaoNylonResponseDTO getById(Long id) {
        ViolaoNylon violaoNylon = violaoNylonRepository.findById(id);
        return ViolaoNylonMapper.toDTO(violaoNylon);
    }

    @Override
    @Transactional
    public void create(ViolaoNylonRequestDTO dto) {
        ViolaoNylon violaoNylon = ViolaoNylonMapper.toEntity(dto);
        violaoNylonRepository.persist(violaoNylon);
    }

    @Override
    @Transactional
    public boolean update(Long id, ViolaoNylonRequestDTO dto) {
        ViolaoNylon violaoNylon = violaoNylonRepository.findById(id);
        if(violaoNylon == null) return false;
        violaoNylon.setNome(dto.nome());
        violaoNylon.setPrecoBase(dto.precoBase());
        violaoNylon.setAnoFabricacao(dto.anoFabricacao());
        violaoNylon.setTipoCordasNylon(dto.tipoCordasNylon());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        ViolaoNylonResponseDTO v = getById(id);
        if (v == null) return false;
        violaoNylonRepository.deleteById(id);
        return true;
    }
}