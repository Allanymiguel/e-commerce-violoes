package br.unitins.ecommerce.perfilBraco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PerfilBracoRequestDTO(

    @NotBlank(message = "Nome é obrigatório")
    String nome,

    String formato,

    @Positive(message = "Espessura deve ser maior do que zero")
    Double espessura
)
{

}
