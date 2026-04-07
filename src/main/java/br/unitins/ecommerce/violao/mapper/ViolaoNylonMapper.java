package br.unitins.ecommerce.violao.mapper;

import br.unitins.ecommerce.violao.dto.ViolaoNylonRequestDTO;
import br.unitins.ecommerce.violao.dto.ViolaoNylonResponseDTO;
import br.unitins.ecommerce.violao.model.ViolaoNylon;

public class ViolaoNylonMapper {

    public static ViolaoNylon toEntity(ViolaoNylonRequestDTO dto) {
        ViolaoNylon v = new ViolaoNylon();
            v.setNome(dto.nome());
            v.setPrecoBase(dto.precoBase());
            v.setAnoFabricacao(dto.anoFabricacao());
            v.setTipoCordasNylon(dto.tipoCordasNylon());

        return v;
    }

    public static ViolaoNylonResponseDTO toDTO(ViolaoNylon entity) {
        return new ViolaoNylonResponseDTO(
            entity.getId(),
            entity.getNome(),
            entity.getPrecoBase(),
            entity.getAnoFabricacao(),
            entity.getTipoCordasNylon()
        );
    }
}