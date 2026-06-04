package br.unitins.ecommerce.violao.repository;

import br.unitins.ecommerce.violao.model.Violao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ViolaoRepository implements PanacheRepository<Violao> {
}
