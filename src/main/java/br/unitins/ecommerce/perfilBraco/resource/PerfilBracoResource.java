package br.unitins.ecommerce.perfilBraco.resource;

import java.util.ArrayList;
import java.util.List;

import br.unitins.ecommerce.perfilBraco.dto.PerfilBracoRequestDTO;
import br.unitins.ecommerce.perfilBraco.dto.PerfilBracoResponseDTO;
import br.unitins.ecommerce.perfilBraco.mapper.PerfilBracoMapper;
import br.unitins.ecommerce.perfilBraco.model.PerfilBraco;
import br.unitins.ecommerce.perfilBraco.service.PerfilBracoServiceImpl;
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

@Path("/perfis-braco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilBracoResource {
    
    @Inject
    PerfilBracoServiceImpl service;

    @POST
    @Path("/cadastrar")
    public PerfilBracoResponseDTO cadastrarPerfilBraco(PerfilBracoRequestDTO dto){
        PerfilBraco perfilBraco = service.create(PerfilBracoMapper.toEntity(dto));
        return PerfilBracoMapper.toResponse(perfilBraco);
    }

    @GET
    public List<PerfilBracoResponseDTO> listarPerfisBraco(){
        List<PerfilBracoResponseDTO> dtoList = new ArrayList<>();
        
        for (PerfilBraco p : service.findAll()) {
            dtoList.add(PerfilBracoMapper.toResponse(p));
        }

        return dtoList;
    }

    @GET
    @Path("/{id}")
    public PerfilBracoResponseDTO listarPerfilBracoPorId(@PathParam("id") Long idPerfilBraco){

        return PerfilBracoMapper.toResponse(service.findById(idPerfilBraco));
    }

    @GET
    @Path("/porNome/{nome}")
    public List<PerfilBracoResponseDTO> listarPerfisBracoPorNome(@PathParam("nome") String nomePerfilBraco){
        List<PerfilBracoResponseDTO> dtoList = new ArrayList<>();
        
        for (PerfilBraco p : service.findByNome(nomePerfilBraco)) {
            dtoList.add(PerfilBracoMapper.toResponse(p));
        }

        return dtoList;
    }

    @PUT
    @Path("/atualizar/{id}")
    public void atualizarPerfilBraco(@PathParam("id") Long idPerfilBraco, PerfilBracoRequestDTO dto){
        service.update(idPerfilBraco, PerfilBracoMapper.toEntity(dto));
    }

    @DELETE
    @Path("/deletar/{id}")
    public void deletarPerfilBraco(@PathParam("id") Long idPerfilBraco){
        service.delete(idPerfilBraco);
    }

}