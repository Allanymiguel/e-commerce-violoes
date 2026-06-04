package br.unitins.ecommerce.cliente.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record ClienteUpdateRequestDTO(

    @NotBlank(message = "O nome completo é obrigatório")
    String nomeCompleto,

    @NotBlank(message = "O telefone é obrigatório")
    String telefone,

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    LocalDate dataNascimento
) {}
