package br.unitins.ecommerce.acessorio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record AcessorioRequestDTO(
    @NotBlank(message = "O nome é obrigatório")
    String nome,
    String descricao,

    @Min(value = 1, message = "O estoque deve ser pelo menos 1")
    Integer quantidadeEstoque,

    @PositiveOrZero
    Double precoUnitario
) {
}