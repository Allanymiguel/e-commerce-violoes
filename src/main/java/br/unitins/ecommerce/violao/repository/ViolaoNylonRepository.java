package br.unitins.ecommerce.violao.repository;

import br.unitins.ecommerce.violao.model.ViolaoNylon;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ViolaoNylonRepository implements PanacheRepository<ViolaoNylon> {
    
    public List<ViolaoNylon> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }
}