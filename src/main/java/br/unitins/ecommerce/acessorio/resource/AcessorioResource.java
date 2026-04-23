package br.unitins.ecommerce.acessorio.resource;

import java.util.ArrayList;
import java.util.List;

import br.unitins.ecommerce.acessorio.dto.AcessorioRequestDTO;
import br.unitins.ecommerce.acessorio.dto.AcessorioResponseDTO;
import br.unitins.ecommerce.acessorio.mapper.AcessorioMapper;
import br.unitins.ecommerce.acessorio.model.Acessorio;
import br.unitins.ecommerce.acessorio.service.AcessorioServiceImpl;
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

@Path("/acessorios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcessorioResource {

    @Inject
    AcessorioServiceImpl service;

    @POST
    @Path("/cadastrar")
    public AcessorioResponseDTO cadastrarAcessorio(AcessorioRequestDTO dto) {
        Acessorio acessorio = service.create(AcessorioMapper.toEntity(dto));
        return AcessorioMapper.toResponse(acessorio);
    }

    @GET
    @Path("/listar")
    public Response listarAcessorios() {
        List<AcessorioResponseDTO> dtoList = new ArrayList<>();
        
        for (Acessorio a : service.findAll()) {
            dtoList.add(AcessorioMapper.toResponse(a));
        }

        return Response.ok(dtoList).build();
    }

    @GET
    @Path("/{id}")
    public Response listarAcessorioPorId(@PathParam("id") Long idAcessorio) {
        return Response.ok(AcessorioMapper.toResponse(service.findById(idAcessorio))).build();
    }

    @GET
    @Path("/listar/{nome}")
    public Response listarAcessoriosPorNome(@PathParam("nome") String nomeAcessorio) {
        List<AcessorioResponseDTO> dtoList = new ArrayList<>();
        
        for (Acessorio a : service.findByNome(nomeAcessorio)) {
            dtoList.add(AcessorioMapper.toResponse(a));
        }

        return Response.ok(dtoList).build();
    }

    @PUT
    @Path("/atualizar/{id}")
    public void atualizarAcessorio(@PathParam("id") Long idAcessorio, AcessorioRequestDTO dto) {
        service.update(idAcessorio, AcessorioMapper.toEntity(dto));
    }

    @DELETE
    @Path("/deletar/{id}")
    public void deletarAcessorio(@PathParam("id") Long idAcessorio) {
        service.delete(idAcessorio);
    }
}