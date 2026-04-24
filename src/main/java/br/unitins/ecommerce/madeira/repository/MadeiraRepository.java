package br.unitins.ecommerce.madeira.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

import br.unitins.ecommerce.madeira.model.Madeira;

@ApplicationScoped
public class MadeiraRepository implements PanacheRepository<Madeira> {

    public PanacheQuery<Madeira> findAll() {
        return find("SELECT m FROM Madeira m");
    }

    public Madeira findById(Long id) {
        return find("SELECT m FROM Madeira m WHERE m.id = ?1", id)
                .firstResult();
    }

    public List<Madeira> findByTipo(String tipo) {
        if (tipo == null) {
            return find("SELECT m FROM Madeira m").list();
        }

        String t = tipo.trim();
        if (t.isEmpty()) {
            return find("SELECT m FROM Madeira m").list();
        }

        return find("SELECT m FROM Madeira m WHERE UPPER(m.tipo) LIKE UPPER(?1)",
                "%" + t + "%")
                .list();
    }

    @Transactional
    public Madeira create(Madeira madeira) {
        persist(madeira);
        return madeira;
    }

    @Transactional
    public void update(Long id, Madeira madeira) {
        Madeira existente = find("SELECT m FROM Madeira m WHERE m.id = ?1", id)
                .firstResult();

        if (existente == null) {
            throw new IllegalArgumentException("Madeira não encontrada para o id: " + id);
        }

        existente.setTipo(madeira.getTipo());
        existente.setDensidade(madeira.getDensidade());
        existente.setSonoridade(madeira.getSonoridade());

    }

    @Transactional
    public boolean delete(Long id) {
        Madeira existente = findById(id);

        if (existente != null) {
            delete(existente);
            return true;
        }
        return false;
    }
}
