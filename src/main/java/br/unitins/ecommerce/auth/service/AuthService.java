package br.unitins.ecommerce.auth.service;

import br.unitins.ecommerce.auth.AuthRequestDTO;
import br.unitins.ecommerce.auth.AuthResponseDTO;

public interface AuthService {

    /**
     * Autentica o usuario e retorna um token JWT em caso de sucesso.
     */
    AuthResponseDTO login(AuthRequestDTO dto);
}
