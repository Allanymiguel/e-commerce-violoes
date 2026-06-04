package br.unitins.ecommerce.auth;

public record KeycloakCredentialDTO(
    String type,
    String value,
    boolean temporary
) {}
