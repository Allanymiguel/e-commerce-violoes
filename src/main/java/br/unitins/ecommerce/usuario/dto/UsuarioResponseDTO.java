package br.unitins.ecommerce.usuario.dto;

import java.time.LocalDate;

import br.unitins.ecommerce.usuario.model.Perfil;

public record UsuarioResponseDTO(
    Long id,
    String login,
    String email,
    String nomeCompleto,
    String cpf,
    String telefone,
    LocalDate dataNascimento,
    Perfil perfil
) {}
