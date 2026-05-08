package br.unitins.ecommerce.auth;

public record AuthResponseDTO(
    String token,
    String tipo
) {}