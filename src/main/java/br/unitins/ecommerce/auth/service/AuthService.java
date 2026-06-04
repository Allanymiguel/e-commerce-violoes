package br.unitins.ecommerce.auth.service;

import br.unitins.ecommerce.auth.AuthRequestDTO;
import br.unitins.ecommerce.auth.AuthResponseDTO;
import br.unitins.ecommerce.auth.ForgotPasswordRequestDTO;

public interface AuthService {

    AuthResponseDTO login(AuthRequestDTO dto);

    void forgotPassword(ForgotPasswordRequestDTO dto);
}
