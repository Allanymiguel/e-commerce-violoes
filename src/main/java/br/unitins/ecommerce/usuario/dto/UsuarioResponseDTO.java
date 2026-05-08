package br.unitins.ecommerce.usuario.dto;

import br.unitins.ecommerce.usuario.model.Perfil;

public record UsuarioResponseDTO(
    Long id,
    String login,
    Perfil perfil
) {}
