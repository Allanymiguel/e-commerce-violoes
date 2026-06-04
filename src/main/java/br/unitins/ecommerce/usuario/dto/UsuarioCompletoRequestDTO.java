package br.unitins.ecommerce.usuario.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record UsuarioCompletoRequestDTO(

    @NotBlank(message = "O login é obrigatório")
    @Size(min = 3, max = 50, message = "O login deve conter entre 3 e 50 caracteres")
    String login,

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    String senha,

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "O nome completo é obrigatório")
    String nomeCompleto,

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 14, message = "CPF inválido")
    String cpf,

    @NotBlank(message = "O telefone é obrigatório")
    String telefone,

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    LocalDate dataNascimento
) {}
