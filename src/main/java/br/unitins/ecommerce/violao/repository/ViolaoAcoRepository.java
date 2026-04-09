package br.unitins.ecommerce.violao.repository;

import br.unitins.ecommerce.violao.model.ViolaoAco;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ViolaoAcoRepository implements PanacheRepository<ViolaoAco> {

    public List<ViolaoAco> listAll() {
        List<ViolaoAco> l = find("SELECT * FROM violoes_aco").list();
        if(l == null) return null;
        return l;
    }
    
    public List<ViolaoAco> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public ViolaoAco create(ViolaoAco violao) {
        if (violao == null) return null;
        persist(violao);
        return violao;
    }
}