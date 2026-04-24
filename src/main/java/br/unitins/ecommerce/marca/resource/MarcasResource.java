package br.unitins.ecommerce.marca.resource;

import java.util.ArrayList;
import java.util.List;

import br.unitins.ecommerce.marca.dto.MarcasRequestDTO;
import br.unitins.ecommerce.marca.dto.MarcasResponseDTO;
import br.unitins.ecommerce.marca.mapper.MarcasMapper;
import br.unitins.ecommerce.marca.model.Marcas;
import br.unitins.ecommerce.marca.service.MarcaServiceImpl;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcasResource {
    
    @Inject
    MarcaServiceImpl service;

    @POST
    @Path("/cadastrar")
    public Response cadastrarMarca(@Valid MarcasRequestDTO dto){
        Marcas marca = service.create(MarcasMapper.toEntity(dto));
        return Response.status(Status.CREATED).entity(MarcasMapper.toResponse(marca)).build();
    }

    @GET
    @Path("/listar")
    public Response listarMarcas(){
        List<MarcasResponseDTO>dtoList = new ArrayList<>();
        
        for (Marcas m : service.findAll()) {
            dtoList.add(MarcasMapper.toResponse(m));
        }

        return Response.ok(dtoList).build();
    }

    @GET
    @Path("/listar/{id}")
    public Response listarMarcasPorId(@PathParam("id") Long idMarca){

        return Response.ok(MarcasMapper.toResponse(service.findById(idMarca))).build();
    }

    @GET
    @Path("/listar/nome/{nome}")
    public Response listarMarcasPorNome(@PathParam("nome") String nomeMarca){
        List<MarcasResponseDTO> dtoList = new ArrayList<>();
        
        for (Marcas m : service.findByNome(nomeMarca)) {
            dtoList.add(MarcasMapper.toResponse(m));
        }

        return Response.ok(dtoList).build();
    }



    @GET
    @Path("/listar/pais/{paisOrigem}")
    public Response listarMarcasPorPais(@PathParam("paisOrigem") String paisOrigem) {
        List<MarcasResponseDTO>dtoList = new ArrayList<>();
        
        for (Marcas m : service.findByPaisOrigem(paisOrigem)) {
            dtoList.add(MarcasMapper.toResponse(m));
        }

        return Response.ok(dtoList).build();
    }



    @PUT
    @Path("/atualizar/{id}")
    public Response atualizarMarca(@PathParam("id") Long idMarca, @Valid MarcasRequestDTO dto){
        service.update(idMarca, MarcasMapper.toEntity(dto));
        return Response.noContent().build();
    }



    @DELETE
    @Path("/deletar/{id}")
    public Response deletarMarca(@PathParam("id") Long idMarca){
        service.delete(idMarca);
        return Response.noContent().build();
    }

}