package br.unitins.ecommerce.pedido.repository;

import java.util.List;
import java.util.Optional;

import br.unitins.ecommerce.pedido.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByKeycloakId(String keycloakId) {
        return find("usuario.keycloakId", keycloakId).list();
    }

    public Optional<Pedido> findByIdAndKeycloakId(Long id, String keycloakId) {
        return find("id = ?1 and usuario.keycloakId = ?2", id, keycloakId).firstResultOptional();
    }
}
