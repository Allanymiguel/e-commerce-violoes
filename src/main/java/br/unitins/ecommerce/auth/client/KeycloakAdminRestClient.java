package br.unitins.ecommerce.auth.client;

import java.util.List;

import br.unitins.ecommerce.auth.KeycloakCreateUserDTO;
import br.unitins.ecommerce.auth.KeycloakRoleDTO;
import br.unitins.ecommerce.auth.KeycloakUpdateUserDTO;
import br.unitins.ecommerce.auth.KeycloakTokenResponseDTO;
import br.unitins.ecommerce.auth.KeycloakUserDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "keycloak-admin")
public interface KeycloakAdminRestClient {

    @POST
    @Path("/realms/{realm}/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    KeycloakTokenResponseDTO obterToken(
            @PathParam("realm") String realm,
            MultivaluedMap<String, String> formParams);

    @GET
    @Path("/admin/realms/{realm}/users")
    @Produces(MediaType.APPLICATION_JSON)
    List<KeycloakUserDTO> buscarPorEmail(
            @HeaderParam("Authorization") String authorization,
            @PathParam("realm") String realm,
            @QueryParam("email") String email,
            @QueryParam("exact") boolean exact);

    @PUT
    @Path("/admin/realms/{realm}/users/{id}/execute-actions-email")
    @Consumes(MediaType.APPLICATION_JSON)
    void enviarAcoesPorEmail(
            @HeaderParam("Authorization") String authorization,
            @PathParam("realm") String realm,
            @PathParam("id") String userId,
            List<String> acoes);

    @POST
    @Path("/admin/realms/{realm}/users")
    @Consumes(MediaType.APPLICATION_JSON)
    Response criarUsuario(
            @HeaderParam("Authorization") String authorization,
            @PathParam("realm") String realm,
            KeycloakCreateUserDTO usuario);

    @DELETE
    @Path("/admin/realms/{realm}/users/{id}")
    void deletarUsuario(
            @HeaderParam("Authorization") String authorization,
            @PathParam("realm") String realm,
            @PathParam("id") String userId);

    @GET
    @Path("/admin/realms/{realm}/roles/{roleName}")
    @Produces(MediaType.APPLICATION_JSON)
    KeycloakRoleDTO buscarRole(
            @HeaderParam("Authorization") String authorization,
            @PathParam("realm") String realm,
            @PathParam("roleName") String roleName);

    @POST
    @Path("/admin/realms/{realm}/users/{id}/role-mappings/realm")
    @Consumes(MediaType.APPLICATION_JSON)
    void atribuirRoles(
            @HeaderParam("Authorization") String authorization,
            @PathParam("realm") String realm,
            @PathParam("id") String userId,
            List<KeycloakRoleDTO> roles);

    @PUT
    @Path("/admin/realms/{realm}/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void atualizarUsuario(
            @HeaderParam("Authorization") String authorization,
            @PathParam("realm") String realm,
            @PathParam("id") String userId,
            KeycloakUpdateUserDTO usuario);

    @DELETE
    @Path("/admin/realms/{realm}/users/{id}/role-mappings/realm")
    @Consumes(MediaType.APPLICATION_JSON)
    void removerRoles(
            @HeaderParam("Authorization") String authorization,
            @PathParam("realm") String realm,
            @PathParam("id") String userId,
            List<KeycloakRoleDTO> roles);
}
