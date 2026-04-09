package br.unitins.ecommerce.violao.repository;

import br.unitins.ecommerce.violao.model.ViolaoNylon;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ViolaoNylonRepository implements PanacheRepository<ViolaoNylon> {
    
    public List<ViolaoNylon> listAll() {
        List<ViolaoNylon> l = find("SELECT * FROM violoes_Nylon").list();
        if(l == null) return null;
        return l;
    }

    public ViolaoNylon findById(Long id) {
        if(id == null) return null;
        return find("SELECT v FROM violoes_Nylon v WHERE v.id = ?1", id)
                .firstResult();
    }
    
    public List<ViolaoNylon> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public ViolaoNylon create(ViolaoNylon violao) {
        if (violao == null) return null;
        persist(violao);
        return violao;
    }

    public void update(Long id, ViolaoNylon violao) {
        ViolaoNylon existente = find("SELECT m FROM violao m WHERE m.id = ?1", id)
                .firstResult();

        if (existente == null) {
            throw new IllegalArgumentException("violao não encontrada para o id: " + id);
        }

        existente.setNome(violao.getNome());
        existente.setPrecoBase(violao.getPrecoBase());
        existente.setAnoFabricacao(violao.getAnoFabricacao());
        existente.setTipoCordasNylon(violao.getTipoCordasNylon());

    }

    public void delete(Long id) {
        ViolaoNylon v = findById(id);

        if (v != null)
            delete(id);
    }
}