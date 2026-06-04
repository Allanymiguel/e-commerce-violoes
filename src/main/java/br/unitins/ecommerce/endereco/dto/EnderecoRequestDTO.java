package br.unitins.ecommerce.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
    String cep,

    @NotBlank(message = "A rua é obrigatória")
    String rua,

    @NotBlank(message = "O número é obrigatório")
    String numero,

    String complemento,

    @NotBlank(message = "O bairro é obrigatório")
    String bairro,

    @NotBlank(message = "A cidade é obrigatória")
    String cidade,

    @NotBlank(message = "O estado é obrigatório")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 letras (ex: TO, GO)")
    String estado
) {}
