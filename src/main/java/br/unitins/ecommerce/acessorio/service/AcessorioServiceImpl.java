package br.unitins.ecommerce.acessorio.service;

import java.util.List;

import br.unitins.ecommerce.acessorio.model.Acessorio;
import br.unitins.ecommerce.acessorio.repository.AcessorioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AcessorioServiceImpl implements AcessorioService {

    @Inject
    private AcessorioRepository repository;

    @Override
    public List<Acessorio> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Acessorio findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Acessorio> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    @Transactional
    public Acessorio create(Acessorio acessorio) {
        return repository.create(acessorio);
    }

    @Override
    @Transactional
    public void update(Long id, Acessorio acessorio) {
        repository.update(id, acessorio);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }
}