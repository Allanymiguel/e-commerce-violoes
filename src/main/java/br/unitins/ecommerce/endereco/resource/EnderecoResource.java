package br.unitins.ecommerce.endereco.resource;

import br.unitins.ecommerce.endereco.dto.EnderecoRequestDTO;
import br.unitins.ecommerce.endereco.service.EnderecoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/clientes/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("CLIENTE")
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @Inject
    JsonWebToken jwt;

    @GET
    public Response listarMeusEnderecos() {
        return Response.ok(service.findByKeycloakId(jwt.getSubject())).build();
    }

    @POST
    public Response adicionar(@Valid EnderecoRequestDTO dto) {
        service.create(jwt.getSubject(), dto);
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid EnderecoRequestDTO dto) {
        service.update(id, jwt.getSubject(), dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id, jwt.getSubject());
        return Response.noContent().build();
    }
}
