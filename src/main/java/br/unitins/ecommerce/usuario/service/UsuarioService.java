package br.unitins.ecommerce.usuario.service;

import java.util.List;

import br.unitins.ecommerce.usuario.model.Usuario;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario findByLogin(String login);
    Usuario create(Usuario usuario);
    void update(Long id, Usuario usuario);
    void delete(Long id);
}