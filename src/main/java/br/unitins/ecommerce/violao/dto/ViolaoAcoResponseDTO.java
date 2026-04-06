package br.unitins.ecommerce.violao.dto;

import br.unitins.ecommerce.violao.model.TipoCordasAco;

public record ViolaoAcoResponseDTO(
    Long id,
    String nome,
    Double precoBase,
    Integer anoFabricacao,
    TipoCordasAco tipoCordasAco
) {
    
}