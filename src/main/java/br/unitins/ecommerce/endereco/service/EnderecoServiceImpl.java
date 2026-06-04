package br.unitins.ecommerce.endereco.service;

import java.util.List;

import br.unitins.ecommerce.endereco.dto.EnderecoRequestDTO;
import br.unitins.ecommerce.endereco.dto.EnderecoResponseDTO;
import br.unitins.ecommerce.endereco.mapper.EnderecoMapper;
import br.unitins.ecommerce.endereco.model.Endereco;
import br.unitins.ecommerce.endereco.repository.EnderecoRepository;
import br.unitins.ecommerce.usuario.model.Usuario;
import br.unitins.ecommerce.usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<EnderecoResponseDTO> findByKeycloakId(String keycloakId) {
        return repository.findByKeycloakId(keycloakId)
                .stream()
                .map(EnderecoMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public void create(String keycloakId, EnderecoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND));
        Endereco endereco = EnderecoMapper.toEntity(dto);
        endereco.setUsuario(usuario);
        repository.persist(endereco);
    }

    @Override
    @Transactional
    public void update(Long id, String keycloakId, EnderecoRequestDTO dto) {
        Endereco endereco = repository.findByIdAndKeycloakId(id, keycloakId)
                .orElseThrow(() -> new WebApplicationException("Endereco nao encontrado", Status.NOT_FOUND));
        endereco.setCep(dto.cep());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
    }

    @Override
    @Transactional
    public void delete(Long id, String keycloakId) {
        Endereco endereco = repository.findByIdAndKeycloakId(id, keycloakId)
                .orElseThrow(() -> new WebApplicationException("Endereco nao encontrado", Status.NOT_FOUND));
        repository.delete(endereco);
    }
}
