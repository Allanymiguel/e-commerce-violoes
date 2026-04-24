package br.unitins.ecommerce.perfilBraco.service;

import java.util.List;

import br.unitins.ecommerce.perfilBraco.model.PerfilBraco;
import br.unitins.ecommerce.perfilBraco.repository.PerfilBracoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PerfilBracoServiceImpl implements PerfilBracoService{

    @Inject
    private PerfilBracoRepository repository;

    @Override
    public List<PerfilBraco> findAll() {
        return repository.findAll().list();
    }

    @Override
    public PerfilBraco findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PerfilBraco> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    @Transactional
    public PerfilBraco create(PerfilBraco perfilBraco) {
        return repository.create(perfilBraco);
    }

    @Override
    @Transactional
    public boolean update(Long id, PerfilBraco perfilBraco) {
        PerfilBraco pb = findById(id);
        if(pb == null) return false;
        repository.update(id, perfilBraco);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        PerfilBraco pb = findById(id);
        if (pb == null) return false;
        repository.delete(id);
        return true;
    }

}