package br.unitins.ecommerce.violao.repository;

import br.unitins.ecommerce.violao.model.ViolaoAco;
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

    public ViolaoAco findById(Long id) {
        if(id == null) return null;
        return find("SELECT v FROM violoes_aco v WHERE v.id = ?1", id)
                .firstResult();
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

    public void update(Long id, ViolaoAco violao) {
        ViolaoAco existente = find("SELECT m FROM violao m WHERE m.id = ?1", id)
                .firstResult();

        if (existente == null) {
            throw new IllegalArgumentException("violao não encontrada para o id: " + id);
        }

        existente.setNome(violao.getNome());
        existente.setPrecoBase(violao.getPrecoBase());
        existente.setAnoFabricacao(violao.getAnoFabricacao());
        existente.setTipoCordasAco(violao.getTipoCordasAco());

    }

    public void delete(Long id) {
        ViolaoAco v = findById(id);

        if (v != null)
            delete(id);
    }
}