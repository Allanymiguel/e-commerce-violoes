package br.unitins.ecommerce.produto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdutoDetalheResponseDTO(
    Long id,
    CategoriaProduto categoria,
    String nome,
    String descricao,
    Double preco,
    Integer quantidadeEstoque,
    String marca,
    Integer anoFabricacao,
    String madeira,
    String tipoViolao,
    String tipoCordas
) {}
