package br.unitins.ecommerce.auth;

import java.util.List;

public record KeycloakCreateUserDTO(
    String username,
    String email,
    String firstName,
    String lastName,
    boolean enabled,
    boolean emailVerified,
    List<String> requiredActions
) {}
