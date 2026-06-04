package br.unitins.ecommerce.listaDesejos.dto;

public record ListaDesejosResponseDTO(
    Long id,
    String nomeItem,
    Double preco,
    String tipo
) {}
