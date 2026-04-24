package br.unitins.ecommerce.violao.service;

import java.util.List;

import br.unitins.ecommerce.violao.dto.ViolaoAcoRequestDTO;
import br.unitins.ecommerce.violao.dto.ViolaoAcoResponseDTO;
import br.unitins.ecommerce.violao.mapper.ViolaoAcoMapper;
import br.unitins.ecommerce.violao.model.ViolaoAco;
import br.unitins.ecommerce.violao.repository.ViolaoAcoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ViolaoAcoServiceImpl implements ViolaoAcoService {

    @Inject
    private ViolaoAcoRepository violaoAcoRepository;

    @Override
    public List<ViolaoAcoResponseDTO> getAll() {
        return violaoAcoRepository.listAll().stream().map(ViolaoAcoMapper::toDTO).toList();
    }

    @Override
    public ViolaoAcoResponseDTO getById(Long id) {
        ViolaoAco violaoAco = violaoAcoRepository.findById(id);
        return ViolaoAcoMapper.toDTO(violaoAco);
    }

    @Override
    @Transactional
    public void create(ViolaoAcoRequestDTO dto) {
        ViolaoAco violaoAco = ViolaoAcoMapper.toEntity(dto);
        violaoAcoRepository.persist(violaoAco);
    }

    @Override
    @Transactional
    public boolean update(Long id, ViolaoAcoRequestDTO dto) {
        ViolaoAco violaoAco = violaoAcoRepository.findById(id);
        if(violaoAco == null) return false;
        violaoAco.setNome(dto.nome());
        violaoAco.setPrecoBase(dto.precoBase());
        violaoAco.setAnoFabricacao(dto.anoFabricacao());
        violaoAco.setTipoCordasAco(dto.tipoCordasAco());

        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        ViolaoAcoResponseDTO v = getById(id);
        if(v == null) return false;
        violaoAcoRepository.deleteById(id);
        return true;
    }

}