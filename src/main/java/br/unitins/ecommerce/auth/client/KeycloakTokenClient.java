package br.unitins.ecommerce.auth.client;

import br.unitins.ecommerce.auth.KeycloakTokenResponseDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "keycloak-token")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface KeycloakTokenClient {

    @POST
    @Path("/protocol/openid-connect/token")
    KeycloakTokenResponseDTO obterToken(MultivaluedMap<String, String> formParams);
}
