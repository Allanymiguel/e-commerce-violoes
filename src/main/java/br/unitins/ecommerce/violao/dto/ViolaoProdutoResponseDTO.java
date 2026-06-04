package br.unitins.ecommerce.violao.dto;

public record ViolaoProdutoResponseDTO(
    Long id,
    String nome,
    Double preco,
    Integer anoFabricacao,
    String marca,
    String madeira,
    String tipo,
    String tipoCordas,
    Integer quantidadeEstoque
) {}
