package br.unitins.ecommerce.auth;

public record KeycloakUpdateUserDTO(
    String username,
    String email,
    String firstName,
    String lastName,
    boolean emailVerified,
    java.util.List<String> requiredActions
) {}
