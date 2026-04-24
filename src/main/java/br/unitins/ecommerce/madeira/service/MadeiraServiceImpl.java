package br.unitins.ecommerce.madeira.service;

import java.util.List;

import br.unitins.ecommerce.madeira.model.Madeira;
import br.unitins.ecommerce.madeira.repository.MadeiraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MadeiraServiceImpl implements MadeiraService{

    @Inject
    private MadeiraRepository repository;

    @Override
    public List<Madeira> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Madeira findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Madeira> findByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    @Override
    @Transactional
    public Madeira create(Madeira madeira) {
        return repository.create(madeira);
    }

    @Override
    @Transactional
    public void update(Long id, Madeira madeira) {
        repository.update(id, madeira);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if(repository.delete(id)) return true;
        return false;
    }

}