package br.unitins.ecommerce.marca.mapper;

import br.unitins.ecommerce.marca.dto.MarcasRequestDTO;
import br.unitins.ecommerce.marca.dto.MarcasResponseDTO;
import br.unitins.ecommerce.marca.model.Marcas;

public class MarcasMapper {

    public static Marcas toEntity (MarcasRequestDTO dto){

        if (dto == null){
            return null;
        }

        Marcas marca = new Marcas();
        marca.setNome(dto.nome());
        marca.setPaisOrigem(dto.paisOrigem());
        marca.setWebsite(dto.website());

        return marca;
    }

    public static MarcasResponseDTO toResponse (Marcas marca) {

        if (marca == null)
            return null;

        return new MarcasResponseDTO(
            marca.getId(),
            marca.getNome(),
            marca.getPaisOrigem(),
            marca.getWebsite()
        );
    }
    
}
