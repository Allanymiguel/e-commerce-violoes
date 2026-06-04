package br.unitins.ecommerce.cliente.service;

import br.unitins.ecommerce.cliente.dto.AlterarSenhaRequestDTO;
import br.unitins.ecommerce.cliente.dto.ClienteUpdateRequestDTO;
import br.unitins.ecommerce.usuario.dto.UsuarioResponseDTO;

public interface ClienteService {
    UsuarioResponseDTO atualizarDados(String keycloakId, ClienteUpdateRequestDTO dto);
    void alterarSenha(String keycloakId, AlterarSenhaRequestDTO dto);
}
