package br.unitins.ecommerce.cliente.service;

import br.unitins.ecommerce.auth.KeycloakCredentialDTO;
import br.unitins.ecommerce.auth.KeycloakTokenResponseDTO;
import br.unitins.ecommerce.auth.client.KeycloakAdminRestClient;
import br.unitins.ecommerce.auth.client.KeycloakTokenClient;
import br.unitins.ecommerce.cliente.dto.AlterarSenhaRequestDTO;
import br.unitins.ecommerce.cliente.dto.ClienteUpdateRequestDTO;
import br.unitins.ecommerce.usuario.dto.UsuarioResponseDTO;
import br.unitins.ecommerce.usuario.mapper.UsuarioMapper;
import br.unitins.ecommerce.usuario.model.Usuario;
import br.unitins.ecommerce.usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    @RestClient
    KeycloakAdminRestClient adminClient;

    @Inject
    @RestClient
    KeycloakTokenClient tokenClient;

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

    @ConfigProperty(name = "quarkus.oidc.client-id")
    String clientId;

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    String clientSecret;

    @Override
    @Transactional
    public UsuarioResponseDTO atualizarDados(String keycloakId, ClienteUpdateRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND));

        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setTelefone(dto.telefone());
        usuario.setDataNascimento(dto.dataNascimento());

        return UsuarioMapper.toResponseDTO(usuario);
    }

    @Override
    public void alterarSenha(String keycloakId, AlterarSenhaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND));

        // Verify current password by trying to authenticate
        try {
            MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
            form.putSingle("grant_type", "password");
            form.putSingle("client_id", clientId);
            form.putSingle("client_secret", clientSecret);
            form.putSingle("username", usuario.getLogin());
            form.putSingle("password", dto.senhaAtual());
            tokenClient.obterToken(form);
        } catch (WebApplicationException e) {
            throw new WebApplicationException("Senha atual incorreta", Status.UNAUTHORIZED);
        }

        // Reset password via Keycloak Admin API
        String bearer = obterTokenAdmin();
        adminClient.resetSenha(bearer, targetRealm, keycloakId,
                new KeycloakCredentialDTO("password", dto.novaSenha(), false));
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
