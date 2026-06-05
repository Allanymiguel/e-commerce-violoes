package br.unitins.ecommerce.produto.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.unitins.ecommerce.acessorio.model.Acessorio;
import br.unitins.ecommerce.acessorio.repository.AcessorioRepository;
import br.unitins.ecommerce.produto.dto.CategoriaProduto;
import br.unitins.ecommerce.produto.dto.ProdutoDetalheResponseDTO;
import br.unitins.ecommerce.produto.dto.ProdutoResponseDTO;
import br.unitins.ecommerce.produto.mapper.ProdutoMapper;
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
    ViolaoRepository violaoRepository;

    @Inject
    AcessorioRepository acessorioRepository;

    @GET
    public Response listar() {
        Stream<ProdutoResponseDTO> violoes = violaoRepository.findAll().stream()
                .map(ProdutoMapper::toResponseDTO);
        Stream<ProdutoResponseDTO> acessorios = acessorioRepository.findAll().stream()
                .map(ProdutoMapper::toResponseDTO);

        List<ProdutoResponseDTO> produtos = new ArrayList<>();
        violoes.forEach(produtos::add);
        acessorios.forEach(produtos::add);
        return Response.ok(produtos).build();
    }

    @GET
    @Path("/{categoria}/{id}")
    public Response buscarPorId(@PathParam("categoria") CategoriaProduto categoria, @PathParam("id") Long id) {
        ProdutoDetalheResponseDTO detalhe = switch (categoria) {
            case VIOLAO -> {
                Violao v = violaoRepository.findById(id);
                yield v == null ? null : ProdutoMapper.toDetalheDTO(v);
            }
            case ACESSORIO -> {
                Acessorio a = acessorioRepository.findById(id);
                yield a == null ? null : ProdutoMapper.toDetalheDTO(a);
            }
        };

        if (detalhe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(detalhe).build();
    }
}
