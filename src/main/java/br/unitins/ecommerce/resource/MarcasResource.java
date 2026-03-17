package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.model.Marcas;
import br.unitins.ecommerce.service.MarcaServiceImpl;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcasResource {
    
    @Inject
    MarcaServiceImpl service;

    @POST
    @Transactional
    @Path("/cadastrar")
    public Response cadastrarMarca(Marcas marca){
        return Response.ok(service.create(marca)).build();
    }

    @GET
    public Response listarMarcas(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response listarMarcasPorId(@PathParam("id") Long idMarca){
        return Response.ok(service.findById(idMarca)).build();
    }

    @GET
    @Path("/{nome}")
    public Response listarMarcasPorNome(@PathParam("nome") String nomeMarca){
        return Response.ok(service.findByNome(nomeMarca)).build();
    }

    @GET
    @Path("/{paisOrigem}")
    public Response listarMarcasPorPais(@PathParam("paisOrigem") String paisOrigem) {
        return Response.ok(service.findByPaisOrigem(paisOrigem)).build();
    }

    @PUT
    @Transactional
    @Path("/atualizar/{id}")
    public void atualizarMarca(@PathParam("id") Long idMarca, Marcas marca){
        service.update(idMarca, marca);
    }

    @DELETE
    @Transactional
    @Path("/deletar/{id}")
    public void deletarMarca(@PathParam("id") Long idMarca){
        service.delete(idMarca);
    }



}
