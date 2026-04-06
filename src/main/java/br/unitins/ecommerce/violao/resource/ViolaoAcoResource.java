package br.unitins.ecommerce.violao.resource;

import br.unitins.ecommerce.violao.dto.ViolaoAcoRequestDTO;
import br.unitins.ecommerce.violao.service.ViolaoAcoService;
import jakarta.inject.Inject;
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

@Path("/violoes/aco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ViolaoAcoResource {

    @Inject
    private ViolaoAcoService violaoAcoService;

    @GET
    public Response getAll() {
        return Response.ok(violaoAcoService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(violaoAcoService.getById(id)).build();
    }

    @POST
    public Response insert(ViolaoAcoRequestDTO dto) {
        violaoAcoService.insert(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ViolaoAcoRequestDTO dto) {
        violaoAcoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        violaoAcoService.delete(id);
        return Response.noContent().build();
    }
}