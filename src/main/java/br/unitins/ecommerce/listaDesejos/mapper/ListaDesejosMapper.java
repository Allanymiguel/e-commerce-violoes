package br.unitins.ecommerce.listaDesejos.mapper;

import br.unitins.ecommerce.listaDesejos.dto.ListaDesejosResponseDTO;
import br.unitins.ecommerce.listaDesejos.model.ListaDesejos;

public class ListaDesejosMapper {

    public static ListaDesejosResponseDTO toResponseDTO(ListaDesejos item) {
        boolean isViolao = item.getViolao() != null;
        return new ListaDesejosResponseDTO(
                item.getId(),
                isViolao ? item.getViolao().getNome() : item.getAcessorio().getNome(),
                isViolao ? item.getViolao().getPrecoBase() : item.getAcessorio().getPrecoUnitario(),
                isViolao ? "VIOLAO" : "ACESSORIO"
        );
    }
}
