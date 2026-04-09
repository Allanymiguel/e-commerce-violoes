package br.unitins.ecommerce.madeira.mapper;

import br.unitins.ecommerce.madeira.dto.MadeiraRequestDTO;
import br.unitins.ecommerce.madeira.dto.MadeiraResponseDTO;
import br.unitins.ecommerce.madeira.model.Madeira;

public class MadeiraMapper {

    public static Madeira toEntity (MadeiraRequestDTO dto){

        if (dto == null){
            return null;
        }

        Madeira madeira = new Madeira();
        madeira.setTipo(dto.tipo());
        madeira.setDensidade(dto.densidade());
        madeira.setSonoridade(dto.sonoridade());

        return madeira;
    }

    public static MadeiraResponseDTO toResponse (Madeira madeira) {

        if (madeira == null)
            return null;

        return new MadeiraResponseDTO(
            madeira.getId(),
            madeira.getTipo(),
            madeira.getDensidade(),
            madeira.getSonoridade()
        );
    }
    
}
