package br.unitins.ecommerce.perfilBraco.resource;

import java.util.ArrayList;
import java.util.List;

import br.unitins.ecommerce.perfilBraco.dto.PerfilBracoRequestDTO;
import br.unitins.ecommerce.perfilBraco.dto.PerfilBracoResponseDTO;
import br.unitins.ecommerce.perfilBraco.mapper.PerfilBracoMapper;
import br.unitins.ecommerce.perfilBraco.model.PerfilBraco;
import br.unitins.ecommerce.perfilBraco.service.PerfilBracoServiceImpl;
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

@Path("/perfis-braco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilBracoResource {
    
    @Inject
    PerfilBracoServiceImpl service;

    @POST
    @Path("/cadastrar")
    public Response cadastrarPerfilBraco(@Valid PerfilBracoRequestDTO dto){
        PerfilBraco perfilBraco = service.create(PerfilBracoMapper.toEntity(dto));
        return Response.status(Status.CREATED).entity(PerfilBracoMapper.toResponse(perfilBraco)).build();
    }

    @GET
    @Path("/listar")
    public Response listarPerfisBraco(){
        List<PerfilBracoResponseDTO> dtoList = new ArrayList<>();
        
        for (PerfilBraco p : service.findAll()) {
            dtoList.add(PerfilBracoMapper.toResponse(p));
        }

        return Response.ok(dtoList).build();
    }

    @GET
    @Path("/listar/{id}")
    public Response listarPerfilBracoPorId(@PathParam("id") Long idPerfilBraco){

        return Response.ok(PerfilBracoMapper.toResponse(service.findById(idPerfilBraco))).build();
    }

    @GET
    @Path("/listar/{nome}")
    public Response listarPerfisBracoPorNome(@PathParam("nome") String nomePerfilBraco){
        List<PerfilBracoResponseDTO> dtoList = new ArrayList<>();
        
        for (PerfilBraco p : service.findByNome(nomePerfilBraco)) {
            dtoList.add(PerfilBracoMapper.toResponse(p));
        }

        return Response.ok(dtoList).build();
    }

    @PUT
    @Path("/atualizar/{id}")
    public Response atualizarPerfilBraco(@PathParam("id") Long idPerfilBraco, @Valid PerfilBracoRequestDTO dto){
        service.update(idPerfilBraco, PerfilBracoMapper.toEntity(dto));
        return Response.noContent().build();
    }

    @DELETE
    @Path("/deletar/{id}")
    public Response deletarPerfilBraco(@PathParam("id") Long idPerfilBraco){
        service.delete(idPerfilBraco);
        return Response.noContent().build();
    }

}