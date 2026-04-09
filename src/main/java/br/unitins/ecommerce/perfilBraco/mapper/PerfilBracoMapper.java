package br.unitins.ecommerce.perfilBraco.mapper;

import br.unitins.ecommerce.perfilBraco.dto.PerfilBracoRequestDTO;
import br.unitins.ecommerce.perfilBraco.dto.PerfilBracoResponseDTO;
import br.unitins.ecommerce.perfilBraco.model.PerfilBraco;

public class PerfilBracoMapper {

    public static PerfilBraco toEntity (PerfilBracoRequestDTO dto){

        if (dto == null){
            return null;
        }

        PerfilBraco perfilBraco = new PerfilBraco();
        perfilBraco.setNome(dto.nome());
        perfilBraco.setFormato(dto.formato());
        perfilBraco.setEspessura(dto.espessura());

        return perfilBraco;
    }

    public static PerfilBracoResponseDTO toResponse (PerfilBraco perfilBraco) {

        if (perfilBraco == null)
            return null;

        return new PerfilBracoResponseDTO(
            perfilBraco.getId(),
            perfilBraco.getNome(),
            perfilBraco.getFormato(),
            perfilBraco.getEspessura()
        );
    }
    
}
