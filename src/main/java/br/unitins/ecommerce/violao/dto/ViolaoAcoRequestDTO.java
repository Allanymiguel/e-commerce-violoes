package br.unitins.ecommerce.violao.dto;

import br.unitins.ecommerce.violao.model.TipoCordasAco;

public record ViolaoAcoRequestDTO(
    String nome,
    Double precoBase,
    Integer anoFabricacao,
    TipoCordasAco tipoCordasAco
) {
    
}