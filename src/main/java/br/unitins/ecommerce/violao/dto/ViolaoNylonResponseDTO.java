package br.unitins.ecommerce.violao.dto;

import br.unitins.ecommerce.violao.model.TipoCordasNylon;

public record ViolaoNylonResponseDTO(
    Long id,
    String nome,
    Double precoBase,
    Integer anoFabricacao,
    TipoCordasNylon tipoCordasNylon
) {
    
}