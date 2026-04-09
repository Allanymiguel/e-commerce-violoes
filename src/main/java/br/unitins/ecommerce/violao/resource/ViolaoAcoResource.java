package br.unitins.ecommerce.violao.resource;

import br.unitins.ecommerce.violao.dto.ViolaoAcoRequestDTO;
import br.unitins.ecommerce.violao.service.ViolaoAcoServiceImpl;
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
    private ViolaoAcoServiceImpl service;

    @GET
    @Path("/listar")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/listar/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(service.getById(id)).build();
    }

    @POST
    @Path("/cadastrar")
    public Response create(ViolaoAcoRequestDTO dto) {
        service.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/atualizar/{id}")
    public Response update(@PathParam("id") Long id, ViolaoAcoRequestDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/deletar/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}