package br.unitins.ecommerce.usuario.service;

import java.util.List;

import br.unitins.ecommerce.auth.KeycloakCreateUserDTO;
import br.unitins.ecommerce.auth.KeycloakCredentialDTO;
import br.unitins.ecommerce.auth.KeycloakRoleDTO;
import br.unitins.ecommerce.auth.KeycloakTokenResponseDTO;
import br.unitins.ecommerce.auth.KeycloakUpdateUserDTO;
import br.unitins.ecommerce.auth.client.KeycloakAdminRestClient;
import br.unitins.ecommerce.usuario.model.Perfil;
import br.unitins.ecommerce.usuario.model.Usuario;
import br.unitins.ecommerce.usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    @RestClient
    KeycloakAdminRestClient adminClient;

    @ConfigProperty(name = "keycloak.admin.realm")
    String adminRealm;

    @ConfigProperty(name = "keycloak.admin.client-id")
    String adminClientId;

    @ConfigProperty(name = "keycloak.admin.username")
    String adminUsername;

    @ConfigProperty(name = "keycloak.admin.password")
    String adminPassword;

    @ConfigProperty(name = "keycloak.admin.target-realm")
    String targetRealm;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Usuario findById(Long id) {
        Usuario u = repository.findById(id);
        if (u == null) {
            throw new WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND);
        }
        return u;
    }

    @Override
    public Usuario findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND));
    }

    @Override
    @Transactional
    public Usuario create(Usuario usuario) {
        if (repository.findByLogin(usuario.getLogin()).isPresent()) {
            throw new WebApplicationException("Login ja existe", Status.CONFLICT);
        }
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new WebApplicationException("Email ja existe", Status.CONFLICT);
        }
        if (usuario.getCpf() != null && repository.findByCpf(usuario.getCpf()).isPresent()) {
            throw new WebApplicationException("CPF ja existe", Status.CONFLICT);
        }

        usuario.setPerfil(Perfil.CLIENTE);
        String senhaPlaintext = usuario.getSenhaHash();
        String bearer = obterTokenAdmin();

        String[] nomes = derivarFirstLastName(usuario);
        KeycloakCreateUserDTO kcUser = new KeycloakCreateUserDTO(
                usuario.getLogin(),
                usuario.getEmail(),
                nomes[0],
                nomes[1],
                true,
                true,
                List.of());

        Response response;
        try {
            response = adminClient.criarUsuario(bearer, targetRealm, kcUser);
        } catch (WebApplicationException e) {
            if (e.getResponse().getStatus() == 409) {
                throw new WebApplicationException("Login ou email ja existe no servidor de autenticacao", Status.CONFLICT);
            }
            throw e;
        }
        String location = response.getHeaderString("Location");
        if (location == null || location.isBlank()) {
            throw new WebApplicationException("Falha ao obter ID do usuario no Keycloak", Status.INTERNAL_SERVER_ERROR);
        }
        String keycloakId = location.substring(location.lastIndexOf('/') + 1);
        usuario.setKeycloakId(keycloakId);

        try {
            adminClient.resetSenha(bearer, targetRealm, keycloakId,
                    new KeycloakCredentialDTO("password", senhaPlaintext, false));

            // Clear any default required actions so the user can login immediately
            adminClient.atualizarUsuario(bearer, targetRealm, keycloakId,
                    new KeycloakUpdateUserDTO(usuario.getLogin(), usuario.getEmail(), nomes[0], nomes[1], true, List.of()));

            KeycloakRoleDTO role = adminClient.buscarRole(bearer, targetRealm, usuario.getPerfil().name());
            adminClient.atribuirRoles(bearer, targetRealm, keycloakId, List.of(role));

            usuario.setSenhaHash("KC_MANAGED");
            repository.persist(usuario);
        } catch (RuntimeException e) {
            try {
                adminClient.deletarUsuario(bearer, targetRealm, keycloakId);
            } catch (RuntimeException ignored) {
            }
            throw e;
        }
        return usuario;
    }

    @Override
    @Transactional
    public void update(Long id, Usuario usuario) {
        Usuario u = findById(id);

        if (!u.getLogin().equals(usuario.getLogin()) &&
            repository.findByLogin(usuario.getLogin()).isPresent()) {
            throw new WebApplicationException("Login ja existe", Status.BAD_REQUEST);
        }

        if (u.getKeycloakId() != null) {
            String bearer = obterTokenAdmin();

            String[] nomesUpd = derivarFirstLastName(usuario);
            adminClient.atualizarUsuario(bearer, targetRealm, u.getKeycloakId(),
                    new KeycloakUpdateUserDTO(usuario.getLogin(), usuario.getEmail(), nomesUpd[0], nomesUpd[1], true, List.of()));

            if (!u.getPerfil().equals(usuario.getPerfil())) {
                KeycloakRoleDTO oldRole = adminClient.buscarRole(bearer, targetRealm, u.getPerfil().name());
                adminClient.removerRoles(bearer, targetRealm, u.getKeycloakId(), List.of(oldRole));

                try {
                    KeycloakRoleDTO newRole = adminClient.buscarRole(bearer, targetRealm, usuario.getPerfil().name());
                    adminClient.atribuirRoles(bearer, targetRealm, u.getKeycloakId(), List.of(newRole));
                } catch (Exception e) {
                    adminClient.atribuirRoles(bearer, targetRealm, u.getKeycloakId(), List.of(oldRole));
                    throw new WebApplicationException("Falha ao atualizar perfil no Keycloak", Status.INTERNAL_SERVER_ERROR);
                }
            }
        }

        u.setLogin(usuario.getLogin());
        u.setEmail(usuario.getEmail());
        u.setPerfil(usuario.getPerfil());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario u = findById(id);
        if (u.getKeycloakId() != null) {
            String bearer = obterTokenAdmin();
            adminClient.deletarUsuario(bearer, targetRealm, u.getKeycloakId());
        }
        repository.delete(u);
    }

    private String[] derivarFirstLastName(Usuario u) {
        String nome = u.getNomeCompleto();
        if (nome == null || nome.isBlank()) {
            String fallback = u.getLogin() != null ? u.getLogin() : "Usuario";
            return new String[] { fallback, fallback };
        }
        String[] partes = nome.trim().split("\\s+", 2);
        if (partes.length == 1) {
            return new String[] { partes[0], partes[0] };
        }
        return partes;
    }

    private String obterTokenAdmin() {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.putSingle("grant_type", "password");
        form.putSingle("client_id", adminClientId);
        form.putSingle("username", adminUsername);
        form.putSingle("password", adminPassword);
        KeycloakTokenResponseDTO token = adminClient.obterToken(adminRealm, form);
        return "Bearer " + token.getAccessToken();
    }
}
