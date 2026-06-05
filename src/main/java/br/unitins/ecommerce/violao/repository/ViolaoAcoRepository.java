package br.unitins.ecommerce.violao.repository;

import java.util.List;

import br.unitins.ecommerce.violao.model.ViolaoAco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ViolaoAcoRepository implements PanacheRepository<ViolaoAco> {

    public List<ViolaoAco> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }
}
