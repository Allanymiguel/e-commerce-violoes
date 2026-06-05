package br.unitins.ecommerce.violao.repository;

import java.util.List;

import br.unitins.ecommerce.violao.model.ViolaoNylon;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ViolaoNylonRepository implements PanacheRepository<ViolaoNylon> {

    public List<ViolaoNylon> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }
}
