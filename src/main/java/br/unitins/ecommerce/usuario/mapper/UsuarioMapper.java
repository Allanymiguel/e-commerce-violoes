package br.unitins.ecommerce.usuario.mapper;

import br.unitins.ecommerce.usuario.dto.UsuarioCompletoRequestDTO;
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

    public static Usuario toEntityFromCompleto(UsuarioCompletoRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(dto.senha());
        usuario.setEmail(dto.email());
        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setDataNascimento(dto.dataNascimento());
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
                usuario.getNomeCompleto(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getDataNascimento(),
                usuario.getPerfil()
        );
    }
}
