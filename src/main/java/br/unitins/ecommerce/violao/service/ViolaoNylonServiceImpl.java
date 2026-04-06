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
    public void insert(ViolaoNylonRequestDTO dto) {
        ViolaoNylon violaoNylon = ViolaoNylonMapper.toEntity(dto);
        violaoNylonRepository.persist(violaoNylon);
    }

    @Override
    @Transactional
    public void update(Long id, ViolaoNylonRequestDTO dto) {
        ViolaoNylon violaoNylon = violaoNylonRepository.findById(id);
        violaoNylon.setNome(dto.nome());
        violaoNylon.setPrecoBase(dto.precoBase());
        violaoNylon.setAnoFabricacao(dto.anoFabricacao());
        violaoNylon.setTipoCordasNylon(dto.tipoCordasNylon());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        violaoNylonRepository.deleteById(id);
    }
}