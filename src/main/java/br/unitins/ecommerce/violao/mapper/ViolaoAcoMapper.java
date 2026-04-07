package br.unitins.ecommerce.violao.mapper;


import br.unitins.ecommerce.violao.dto.ViolaoAcoRequestDTO;
import br.unitins.ecommerce.violao.dto.ViolaoAcoResponseDTO;
import br.unitins.ecommerce.violao.model.ViolaoAco;

public class ViolaoAcoMapper {

    public static ViolaoAco toEntity(ViolaoAcoRequestDTO dto) {
        ViolaoAco v = new ViolaoAco();
        v.setNome(dto.nome());
        v.setPrecoBase(dto.precoBase());
        v.setAnoFabricacao(dto.anoFabricacao());
        return v;
    }

    public static ViolaoAcoResponseDTO toDTO(ViolaoAco entity) {
        return new ViolaoAcoResponseDTO(
            entity.getId(),
            entity.getNome(),
            entity.getPrecoBase(),
            entity.getAnoFabricacao(),
            entity.getTipoCordasAco()
        );
    }
}