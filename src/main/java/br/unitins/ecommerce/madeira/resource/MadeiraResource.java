package br.unitins.ecommerce.madeira.resource;

import java.util.ArrayList;
import java.util.List;

import br.unitins.ecommerce.madeira.dto.MadeiraRequestDTO;
import br.unitins.ecommerce.madeira.dto.MadeiraResponseDTO;
import br.unitins.ecommerce.madeira.mapper.MadeiraMapper;
import br.unitins.ecommerce.madeira.model.Madeira;
import br.unitins.ecommerce.madeira.service.MadeiraServiceImpl;
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
import jakarta.ws.rs.core.Response.Status;

@Path("/madeiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MadeiraResource {
    
    @Inject
    MadeiraServiceImpl service;

    @POST
    @Path("/cadastrar")
    public Response cadastrarMadeira(MadeiraRequestDTO dto){
        Madeira madeira = service.create(MadeiraMapper.toEntity(dto));
        return Response.status(Status.CREATED).entity(MadeiraMapper.toResponse(madeira)).build();
    }

    @GET
    @Path("/listar")
    public Response listarMadeiras(){
        List<MadeiraResponseDTO> dtoList = new ArrayList<>();
        
        for (Madeira m : service.findAll()) {
            dtoList.add(MadeiraMapper.toResponse(m));
        }

        return Response.ok(dtoList).build();
    }

    @GET
    @Path("/listar/{id}")
    public Response listarMadeiraPorId(@PathParam("id") Long idMadeira){
        return Response.ok(MadeiraMapper.toResponse(service.findById(idMadeira))).build();
    }

    @GET
    @Path("/listar/{tipo}")
    public List<MadeiraResponseDTO> listarMadeirasPorTipo(@PathParam("tipo") String tipoMadeira){
        List<MadeiraResponseDTO> dtoList = new ArrayList<>();
        
        for (Madeira m : service.findByTipo(tipoMadeira)) {
            dtoList.add(MadeiraMapper.toResponse(m));
        }

        return dtoList;
    }

    @PUT
    @Path("/atualizar/{id}")
    public void atualizarMadeira(@PathParam("id") Long idMadeira, MadeiraRequestDTO dto){
        service.update(idMadeira, MadeiraMapper.toEntity(dto));
    }

    @DELETE
    @Path("/deletar/{id}")
    public void deletarMadeira(@PathParam("id") Long idMadeira){
        service.delete(idMadeira);
    }

}