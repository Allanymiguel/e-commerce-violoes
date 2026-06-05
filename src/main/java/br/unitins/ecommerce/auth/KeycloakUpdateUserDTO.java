package br.unitins.ecommerce.auth;

public record KeycloakUpdateUserDTO(String username, String email, boolean emailVerified, java.util.List<String> requiredActions) {}
