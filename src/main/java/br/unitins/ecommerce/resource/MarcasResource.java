package br.unitins.ecommerce.resource;

import java.util.ArrayList;
import java.util.List;

import br.unitins.ecommerce.dto.MarcasRequestDTO;
import br.unitins.ecommerce.dto.MarcasResponseDTO;
import br.unitins.ecommerce.mapper.MarcasMapper;
import br.unitins.ecommerce.model.Marcas;
import br.unitins.ecommerce.service.MarcaServiceImpl;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcasResource {
    
    @Inject
    MarcaServiceImpl service;




    @POST
    @Path("/cadastrar")
    public MarcasResponseDTO cadastrarMarca(MarcasRequestDTO dto){
        Marcas marca = service.create(MarcasMapper.toEntity(dto));
        return MarcasMapper.toResponse(marca);
    }



    @GET
    public List <MarcasResponseDTO> listarMarcas(){
        List<MarcasResponseDTO>dtoList = new ArrayList<>();
        
        for (Marcas m : service.findAll()) {
            dtoList.add(MarcasMapper.toResponse(m));
        }

        return dtoList;
    }



    @GET
    @Path("/{id}")
    public MarcasResponseDTO listarMarcasPorId(@PathParam("id") Long idMarca){

        return MarcasMapper.toResponse(service.findById(idMarca));
    }



    @GET
    @Path("/porNome/{nome}")
    public List <MarcasResponseDTO> listarMarcasPorNome(@PathParam("nome") String nomeMarca){
        List<MarcasResponseDTO> dtoList = new ArrayList<>();
        
        for (Marcas m : service.findByNome(nomeMarca)) {
            dtoList.add(MarcasMapper.toResponse(m));
        }

        return dtoList;
    }



    @GET
    @Path("/porPais/{paisOrigem}")
    public List<MarcasResponseDTO> listarMarcasPorPais(@PathParam("paisOrigem") String paisOrigem) {
        List<MarcasResponseDTO>dtoList = new ArrayList<>();
        
        for (Marcas m : service.findByPaisOrigem(paisOrigem)) {
            dtoList.add(MarcasMapper.toResponse(m));
        }

        return dtoList;
    }



    @PUT
    @Path("/atualizar/{id}")
    public void atualizarMarca(@PathParam("id") Long idMarca, Marcas marca){
        service.update(idMarca, marca);
    }



    @DELETE
    @Path("/deletar/{id}")
    public void deletarMarca(@PathParam("id") Long idMarca){
        service.delete(idMarca);
    }

}