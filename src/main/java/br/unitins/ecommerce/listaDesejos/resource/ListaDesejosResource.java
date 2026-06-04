package br.unitins.ecommerce.listaDesejos.resource;

import br.unitins.ecommerce.listaDesejos.dto.ListaDesejosRequestDTO;
import br.unitins.ecommerce.listaDesejos.service.ListaDesejosService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/clientes/lista-desejos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("CLIENTE")
public class ListaDesejosResource {

    @Inject
    ListaDesejosService service;

    @Inject
    JsonWebToken jwt;

    @GET
    public Response listar() {
        return Response.ok(service.findByKeycloakId(jwt.getSubject())).build();
    }

    @POST
    public Response adicionar(ListaDesejosRequestDTO dto) {
        service.create(jwt.getSubject(), dto);
        return Response.status(Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.delete(id, jwt.getSubject());
        return Response.noContent().build();
    }
}
