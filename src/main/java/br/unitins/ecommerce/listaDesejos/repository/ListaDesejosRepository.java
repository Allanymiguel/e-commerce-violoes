package br.unitins.ecommerce.listaDesejos.repository;

import java.util.List;
import java.util.Optional;

import br.unitins.ecommerce.listaDesejos.model.ListaDesejos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListaDesejosRepository implements PanacheRepository<ListaDesejos> {

    public List<ListaDesejos> findByKeycloakId(String keycloakId) {
        return find("usuario.keycloakId", keycloakId).list();
    }

    public Optional<ListaDesejos> findByIdAndKeycloakId(Long id, String keycloakId) {
        return find("id = ?1 and usuario.keycloakId = ?2", id, keycloakId).firstResultOptional();
    }
}
