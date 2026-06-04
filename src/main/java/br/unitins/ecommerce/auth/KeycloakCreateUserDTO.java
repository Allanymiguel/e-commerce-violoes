package br.unitins.ecommerce.auth;

import java.util.List;

public record KeycloakCreateUserDTO(
    String username,
    String email,
    boolean enabled,
    List<KeycloakCredentialDTO> credentials
) {}
