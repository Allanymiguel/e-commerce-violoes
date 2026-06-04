package br.unitins.ecommerce.listaDesejos.service;

import java.util.List;

import br.unitins.ecommerce.acessorio.repository.AcessorioRepository;
import br.unitins.ecommerce.listaDesejos.dto.ListaDesejosRequestDTO;
import br.unitins.ecommerce.listaDesejos.dto.ListaDesejosResponseDTO;
import br.unitins.ecommerce.listaDesejos.mapper.ListaDesejosMapper;
import br.unitins.ecommerce.listaDesejos.model.ListaDesejos;
import br.unitins.ecommerce.listaDesejos.repository.ListaDesejosRepository;
import br.unitins.ecommerce.usuario.model.Usuario;
import br.unitins.ecommerce.usuario.repository.UsuarioRepository;
import br.unitins.ecommerce.violao.repository.ViolaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class ListaDesejosServiceImpl implements ListaDesejosService {

    @Inject ListaDesejosRepository repository;
    @Inject UsuarioRepository usuarioRepository;
    @Inject ViolaoRepository violaoRepository;
    @Inject AcessorioRepository acessorioRepository;

    @Override
    public List<ListaDesejosResponseDTO> findByKeycloakId(String keycloakId) {
        return repository.findByKeycloakId(keycloakId).stream()
                .map(ListaDesejosMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public void create(String keycloakId, ListaDesejosRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND));

        ListaDesejos item = new ListaDesejos();
        item.setUsuario(usuario);

        if (dto.idViolao() != null) {
            var violao = violaoRepository.findById(dto.idViolao());
            if (violao == null)
                throw new WebApplicationException("Violao nao encontrado", Status.NOT_FOUND);
            item.setViolao(violao);
        } else if (dto.idAcessorio() != null) {
            var acessorio = acessorioRepository.findById(dto.idAcessorio());
            if (acessorio == null)
                throw new WebApplicationException("Acessorio nao encontrado", Status.NOT_FOUND);
            item.setAcessorio(acessorio);
        } else {
            throw new WebApplicationException("Informe idViolao ou idAcessorio", Status.BAD_REQUEST);
        }

        repository.persist(item);
    }

    @Override
    @Transactional
    public void delete(Long id, String keycloakId) {
        ListaDesejos item = repository.findByIdAndKeycloakId(id, keycloakId)
                .orElseThrow(() -> new WebApplicationException("Item nao encontrado na lista de desejos", Status.NOT_FOUND));
        repository.delete(item);
    }
}
