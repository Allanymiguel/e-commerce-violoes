package br.unitins.ecommerce.auth.service;

import java.util.List;

import br.unitins.ecommerce.auth.AuthRequestDTO;
import br.unitins.ecommerce.auth.AuthResponseDTO;
import br.unitins.ecommerce.auth.ForgotPasswordRequestDTO;
import br.unitins.ecommerce.auth.KeycloakTokenResponseDTO;
import br.unitins.ecommerce.auth.KeycloakUserDTO;
import br.unitins.ecommerce.auth.client.KeycloakAdminRestClient;
import br.unitins.ecommerce.auth.client.KeycloakTokenClient;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    @Inject
    @RestClient
    KeycloakTokenClient tokenClient;

    @Inject
    @RestClient
    KeycloakAdminRestClient adminClient;

    @ConfigProperty(name = "quarkus.oidc.client-id")
    String clientId;

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    String clientSecret;

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
    public AuthResponseDTO login(AuthRequestDTO dto) {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.putSingle("grant_type", "password");
        form.putSingle("client_id", clientId);
        form.putSingle("client_secret", clientSecret);
        form.putSingle("username", dto.login());
        form.putSingle("password", dto.senha());

        try {
            KeycloakTokenResponseDTO tokenResponse = tokenClient.obterToken(form);
            return new AuthResponseDTO(tokenResponse.getAccessToken(), "Bearer");
        } catch (WebApplicationException e) {
            int status = e.getResponse().getStatus();
            String body;
            try {
                body = e.getResponse().readEntity(String.class);
            } catch (RuntimeException readErr) {
                body = "<corpo indisponivel: " + readErr.getMessage() + ">";
            }
            Log.warnf("Falha no login Keycloak (login=%s) status=%d body=%s", dto.login(), status, body);
            throw new WebApplicationException("Credenciais invalidas", Status.UNAUTHORIZED);
        }
    }

    @Override
    public void forgotPassword(ForgotPasswordRequestDTO dto) {
        try {
            // Obter token de administrador no realm master usando admin-cli (cliente publico)
            MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
            form.putSingle("grant_type", "password");
            form.putSingle("client_id", adminClientId);
            form.putSingle("username", adminUsername);
            form.putSingle("password", adminPassword);

            KeycloakTokenResponseDTO adminToken = adminClient.obterToken(adminRealm, form);
            String bearer = "Bearer " + adminToken.getAccessToken();

            List<KeycloakUserDTO> usuarios = adminClient.buscarPorEmail(bearer, targetRealm, dto.email(), true);
            if (!usuarios.isEmpty()) {
                adminClient.enviarAcoesPorEmail(bearer, targetRealm, usuarios.get(0).id(), List.of("UPDATE_PASSWORD"));
            }
        } catch (Exception e) {
            Log.warnf("Erro ao processar redefinicao de senha: %s", e.getMessage());
        }
    }
}
