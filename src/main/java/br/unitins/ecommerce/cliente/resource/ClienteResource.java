package br.unitins.ecommerce.cliente.resource;

import br.unitins.ecommerce.cliente.dto.AlterarSenhaRequestDTO;
import br.unitins.ecommerce.cliente.dto.ClienteUpdateRequestDTO;
import br.unitins.ecommerce.cliente.service.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/clientes/me")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("CLIENTE")
public class ClienteResource {

    @Inject
    ClienteService service;

    @Inject
    JsonWebToken jwt;

    @PUT
    public Response atualizarDados(@Valid ClienteUpdateRequestDTO dto) {
        return Response.ok(service.atualizarDados(jwt.getSubject(), dto)).build();
    }

    @PUT
    @Path("/senha")
    public Response alterarSenha(@Valid AlterarSenhaRequestDTO dto) {
        service.alterarSenha(jwt.getSubject(), dto);
        return Response.noContent().build();
    }
}
