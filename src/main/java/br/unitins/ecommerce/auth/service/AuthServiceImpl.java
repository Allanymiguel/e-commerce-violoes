package br.unitins.ecommerce.auth.service;

import br.unitins.ecommerce.auth.AuthRequestDTO;
import br.unitins.ecommerce.auth.AuthResponseDTO;
import br.unitins.ecommerce.usuario.model.Usuario;
import br.unitins.ecommerce.usuario.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    @Inject
    UsuarioService usuarioService;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        Usuario usuario = usuarioService.findByLogin(dto.login());

        if (!hashService.verificarBcrypt(dto.senha(), usuario.getSenhaHash())) {
            throw new WebApplicationException("Credenciais invalidas", Status.UNAUTHORIZED);
        }

        String token = jwtService.gerarToken(usuario);
        return new AuthResponseDTO(token, "Bearer");
    }
}
