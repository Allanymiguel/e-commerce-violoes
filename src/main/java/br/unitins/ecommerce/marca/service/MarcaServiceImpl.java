package br.unitins.ecommerce.marca.service;

import java.util.List;

import br.unitins.ecommerce.marca.model.Marcas;
import br.unitins.ecommerce.marca.repository.MarcasRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
    @Transactional
    public Marcas create(Marcas marca) {
        return repository.create(marca);
    }

    @Override
    @Transactional
    public void update(Long id, Marcas marca) {
        repository.update(id, marca);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if(findById(id) == null) return false;
        repository.delete(id);
        return true;
    }

    
    
}
