package br.unitins.ecommerce.perfilBraco.service;

import java.util.List;

import br.unitins.ecommerce.perfilBraco.model.PerfilBraco;

public interface PerfilBracoService {
    
    List<PerfilBraco> findAll();
    PerfilBraco findById(Long id);
    List<PerfilBraco> findByNome(String nome);
    PerfilBraco create(PerfilBraco perfilBraco);
    boolean update(Long id, PerfilBraco perfilBraco);
    boolean delete(Long id);

}