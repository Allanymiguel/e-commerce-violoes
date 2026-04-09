package br.unitins.ecommerce.acessorio.dto;

public record AcessorioRequestDTO(
    String nome,
    String descricao,
    Integer quantidadeEstoque,
    Double precoUnitario
) {
}