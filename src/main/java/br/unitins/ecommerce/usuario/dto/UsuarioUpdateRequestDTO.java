package br.unitins.ecommerce.usuario.dto;

import br.unitins.ecommerce.usuario.model.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateRequestDTO(

    @NotBlank(message = "O login é obrigatório")
    @Size(min = 3, max = 50, message = "O login deve conter entre 3 e 50 caracteres")
    String login,

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotNull(message = "O perfil é obrigatório")
    Perfil perfil
) {}
