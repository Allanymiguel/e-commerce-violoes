package br.unitins.ecommerce.usuario.resource;

import java.util.List;

import br.unitins.ecommerce.usuario.dto.UsuarioRequestDTO;
import br.unitins.ecommerce.usuario.dto.UsuarioResponseDTO;
import br.unitins.ecommerce.usuario.mapper.UsuarioMapper;
import br.unitins.ecommerce.usuario.model.Usuario;
import br.unitins.ecommerce.usuario.service.UsuarioServiceImpl;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioServiceImpl service;

    @GET
    @RolesAllowed("ADMIN")
    public Response buscarTodos() {
        List<UsuarioResponseDTO> lista = service.findAll()
                .stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response buscarPeloId(@PathParam("id") Long id) {
        Usuario usuario = service.findById(id);
        if (usuario == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(UsuarioMapper.toResponseDTO(usuario)).build();
    }

    @GET
    @Path("/login/{login}")
    @RolesAllowed("ADMIN")
    public Response buscarPeloLogin(@PathParam("login") String login) {
        Usuario usuario = service.findByLogin(login);
        return Response.ok(UsuarioMapper.toResponseDTO(usuario)).build();
    }

    @POST
    // @RolesAllowed("ADMIN") desabilitei para poder criar meu perfil de teste
    public Response criar(@Valid UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario usuarioCriado = service.create(usuario);
        return Response.status(Status.CREATED)
                .entity(UsuarioMapper.toResponseDTO(usuarioCriado))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response atualizar(@PathParam("id") Long id, @Valid UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        service.update(id, usuario);
        Usuario usuarioAtualizado = service.findById(id);
        return Response.ok(UsuarioMapper.toResponseDTO(usuarioAtualizado)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
