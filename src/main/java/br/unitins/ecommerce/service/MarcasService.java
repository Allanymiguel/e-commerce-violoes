package br.unitins.ecommerce.service;

import java.util.List;

import br.unitins.ecommerce.model.Marcas;

public interface MarcasService {
    
    List<Marcas> findAll();
    Marcas findById(Long id);
    List<Marcas> findByPaisOrigem(String paisOrigem);
    List<Marcas> findByNome(String nome);
    Marcas create(Marcas marca);
    void update(Long id, Marcas marca);
    void delete(Long id);

}
