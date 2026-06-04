package br.unitins.ecommerce.pedido.resource;

import br.unitins.ecommerce.pedido.dto.PedidoRequestDTO;
import br.unitins.ecommerce.pedido.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/clientes/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("CLIENTE")
public class PedidoResource {

    @Inject
    PedidoService service;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response realizarCompra(@Valid PedidoRequestDTO dto) {
        return Response.status(Status.CREATED)
                .entity(service.create(jwt.getSubject(), dto))
                .build();
    }

    @GET
    public Response meuHistorico() {
        return Response.ok(service.findByKeycloakId(jwt.getSubject())).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(service.findByIdAndKeycloakId(id, jwt.getSubject())).build();
    }
}
