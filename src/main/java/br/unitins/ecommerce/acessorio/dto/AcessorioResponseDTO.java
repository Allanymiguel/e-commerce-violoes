package br.unitins.ecommerce.acessorio.dto;

public record AcessorioResponseDTO(
    Long id,
    String nome,
    String descricao,
    Integer quantidadeEstoque,
    Double precoUnitario
) {
}