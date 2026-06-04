package br.unitins.ecommerce.endereco.repository;

import java.util.List;
import java.util.Optional;

import br.unitins.ecommerce.endereco.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByKeycloakId(String keycloakId) {
        return find("usuario.keycloakId", keycloakId).list();
    }

    public Optional<Endereco> findByIdAndKeycloakId(Long id, String keycloakId) {
        return find("id = ?1 and usuario.keycloakId = ?2", id, keycloakId).firstResultOptional();
    }
}
