package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.Marcas;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MarcasRepository implements PanacheRepository<Marcas> {

    public PanacheQuery<Marcas> findAll() {
        return find("SELECT m FROM Marcas m");
    }

    public Marcas findById(Long id) {
        return find("SELECT m FROM Marcas m WHERE m.id = ?1", id)
                .firstResult();
    }

    public Marcas findByPaisOrigem(String paisOrigem) {
        if (paisOrigem == null) return null;

        String p = paisOrigem.trim();
        if (p.isEmpty()) return null;

        return find("SELECT m FROM Marcas m WHERE UPPER(m.paisOrigem) = UPPER(?1)", p)
                .firstResult();
    }

    public List<Marcas> findByNome(String nome) {
        if (nome == null) {
            // se preferir, pode retornar List.of() (Java 9+)
            return find("SELECT m FROM Marcas m").list();
        }

        String n = nome.trim();
        if (n.isEmpty()) {
            return find("SELECT m FROM Marcas m").list();
        }

        // LIKE com %...% (busca por parte do nome)
        return find("SELECT m FROM Marcas m WHERE UPPER(m.nome) LIKE UPPER(?1)",
                "%" + n + "%")
                .list();
    }

    @Transactional
    public Marcas create(Marcas marca) {
        persist(marca);
        return marca;
    }

    @Transactional
    public void update(Long id, Marcas marca) {
        Marcas existente = find("SELECT m FROM Marcas m WHERE m.id = ?1", id)
                .firstResult();

        if (existente == null) {
            throw new IllegalArgumentException("Marca não encontrada para o id: " + id);
        }

        existente.setNome(marca.getNome());
        existente.setPaisOrigem(marca.getPaisOrigem());
        existente.setWebsite(marca.getWebsite());

    }

    @Transactional
    public void delete(Long id) {
        Marcas existente = findById(id);

        if (existente != null) {
            delete(existente);
        }
    }
}