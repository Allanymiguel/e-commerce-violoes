package br.unitins.ecommerce.usuario.mapper;

import br.unitins.ecommerce.usuario.dto.UsuarioRequestDTO;
import br.unitins.ecommerce.usuario.dto.UsuarioResponseDTO;
import br.unitins.ecommerce.usuario.dto.UsuarioUpdateRequestDTO;
import br.unitins.ecommerce.usuario.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(dto.senha());
        usuario.setEmail(dto.email());
        return usuario;
    }

    public static Usuario toEntityFromUpdate(UsuarioUpdateRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setEmail(dto.email());
        usuario.setPerfil(dto.perfil());
        return usuario;
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getEmail(),
                usuario.getPerfil()
        );
    }
}
