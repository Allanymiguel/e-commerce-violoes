package br.unitins.ecommerce.produto.resource;

import java.util.List;

import br.unitins.ecommerce.violao.dto.ViolaoProdutoResponseDTO;
import br.unitins.ecommerce.violao.mapper.ViolaoProdutoMapper;
import br.unitins.ecommerce.violao.model.Violao;
import br.unitins.ecommerce.violao.repository.ViolaoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ViolaoRepository repository;

    @GET
    public Response listar() {
        List<ViolaoProdutoResponseDTO> produtos = repository.findAll().list()
                .stream()
                .map(ViolaoProdutoMapper::toResponseDTO)
                .toList();
        return Response.ok(produtos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Violao v = repository.findById(id);
        if (v == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(ViolaoProdutoMapper.toResponseDTO(v)).build();
    }
}
