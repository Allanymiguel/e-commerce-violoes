package br.unitins.ecommerce.acessorio.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

import br.unitins.ecommerce.acessorio.model.Acessorio;

@ApplicationScoped
public class AcessorioRepository implements PanacheRepository<Acessorio> {

    public PanacheQuery<Acessorio> findAll() {
        return find("SELECT a FROM Acessorio a");
    }

    public Acessorio findById(Long id) {
        return find("SELECT a FROM Acessorio a WHERE a.id = ?1", id)
                .firstResult();
    }

    public List<Acessorio> findByNome(String nome) {
        if (nome == null) {
            return find("SELECT a FROM Acessorio a").list();
        }

        String n = nome.trim();
        if (n.isEmpty()) {
            return find("SELECT a FROM Acessorio a").list();
        }

        return find("SELECT a FROM Acessorio a WHERE UPPER(a.nome) LIKE UPPER(?1)",
                "%" + n + "%")
                .list();
    }

    @Transactional
    public Acessorio create(Acessorio acessorio) {
        persist(acessorio);
        return acessorio;
    }

    @Transactional
    public void update(Long id, Acessorio acessorio) {
        Acessorio existente = findById(id);

        if (existente == null) {
            throw new IllegalArgumentException("Acessório não encontrado para o id: " + id);
        }

        existente.setNome(acessorio.getNome());
        existente.setDescricao(acessorio.getDescricao());
        existente.setQuantidadeEstoque(acessorio.getQuantidadeEstoque());
        existente.setPrecoUnitario(acessorio.getPrecoUnitario());
    }

    @Transactional
    public void delete(Long id) {
        Acessorio existente = findById(id);

        if (existente != null) {
            delete(existente);
        }
    }
}