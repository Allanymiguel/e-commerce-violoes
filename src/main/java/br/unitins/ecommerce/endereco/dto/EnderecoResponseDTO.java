package br.unitins.ecommerce.endereco.dto;

public record EnderecoResponseDTO(
    Long id,
    String cep,
    String rua,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado
) {}
