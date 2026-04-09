package br.unitins.ecommerce.perfilBraco.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

import br.unitins.ecommerce.perfilBraco.model.PerfilBraco;

@ApplicationScoped
public class PerfilBracoRepository implements PanacheRepository<PerfilBraco> {

    public PanacheQuery<PerfilBraco> findAll() {
        return find("SELECT p FROM PerfilBraco p");
    }

    public PerfilBraco findById(Long id) {
        return find("SELECT p FROM PerfilBraco p WHERE p.id = ?1", id)
                .firstResult();
    }

    public List<PerfilBraco> findByNome(String nome) {
        if (nome == null) {
            return find("SELECT p FROM PerfilBraco p").list();
        }

        String n = nome.trim();
        if (n.isEmpty()) {
            return find("SELECT p FROM PerfilBraco p").list();
        }

        return find("SELECT p FROM PerfilBraco p WHERE UPPER(p.nome) LIKE UPPER(?1)",
                "%" + n + "%")
                .list();
    }

    @Transactional
    public PerfilBraco create(PerfilBraco perfilBraco) {
        persist(perfilBraco);
        return perfilBraco;
    }

    @Transactional
    public void update(Long id, PerfilBraco perfilBraco) {
        PerfilBraco existente = find("SELECT p FROM PerfilBraco p WHERE p.id = ?1", id)
                .firstResult();

        if (existente == null) {
            throw new IllegalArgumentException("PerfilBraco não encontrado para o id: " + id);
        }

        existente.setNome(perfilBraco.getNome());
        existente.setFormato(perfilBraco.getFormato());
        existente.setEspessura(perfilBraco.getEspessura());

    }

    @Transactional
    public void delete(Long id) {
        PerfilBraco existente = findById(id);

        if (existente != null) {
            delete(existente);
        }
    }
}
