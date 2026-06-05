package br.unitins.ecommerce.produto.dto;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    Double preco,
    String marca,
    CategoriaProduto categoria,
    Integer quantidadeEstoque
) {}
