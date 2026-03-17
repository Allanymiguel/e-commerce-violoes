package br.unitins.ecommerce.service;

import java.util.List;

import br.unitins.ecommerce.model.Marcas;
import br.unitins.ecommerce.repository.MarcasRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MarcaServiceImpl implements MarcasService{

    @Inject
    private MarcasRepository repository;

    @Override
    public List<Marcas> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Marcas findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Marcas> findByPaisOrigem(String paisOrigem) {
        return repository.findByPaisOrigem(paisOrigem).list();
    }

    @Override
    public List<Marcas> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public Marcas create(Marcas marca) {
        return repository.create(marca);
    }

    @Override
    public void update(Long id, Marcas marca) {
        repository.update(id, marca);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    
    
}
