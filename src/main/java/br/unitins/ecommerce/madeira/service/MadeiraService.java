package br.unitins.ecommerce.madeira.service;

import java.util.List;

import br.unitins.ecommerce.madeira.model.Madeira;

public interface MadeiraService {
    
    List<Madeira> findAll();
    Madeira findById(Long id);
    List<Madeira> findByTipo(String tipo);
    Madeira create(Madeira madeira);
    void update(Long id, Madeira madeira);
    void delete(Long id);

}