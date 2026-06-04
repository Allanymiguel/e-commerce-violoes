package br.unitins.ecommerce.pedido.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.ecommerce.acessorio.model.Acessorio;
import br.unitins.ecommerce.acessorio.repository.AcessorioRepository;
import br.unitins.ecommerce.endereco.repository.EnderecoRepository;
import br.unitins.ecommerce.pedido.dto.ItemPedidoRequestDTO;
import br.unitins.ecommerce.pedido.dto.PedidoRequestDTO;
import br.unitins.ecommerce.pedido.dto.PedidoResponseDTO;
import br.unitins.ecommerce.pedido.mapper.PedidoMapper;
import br.unitins.ecommerce.pedido.model.ItemPedido;
import br.unitins.ecommerce.pedido.model.Pedido;
import br.unitins.ecommerce.pedido.repository.PedidoRepository;
import br.unitins.ecommerce.usuario.model.Usuario;
import br.unitins.ecommerce.usuario.repository.UsuarioRepository;
import br.unitins.ecommerce.violao.model.Violao;
import br.unitins.ecommerce.violao.repository.ViolaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject PedidoRepository pedidoRepository;
    @Inject UsuarioRepository usuarioRepository;
    @Inject EnderecoRepository enderecoRepository;
    @Inject ViolaoRepository violaoRepository;
    @Inject AcessorioRepository acessorioRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(String keycloakId, PedidoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);

        if (dto.idEndereco() != null) {
            pedido.setEndereco(
                enderecoRepository.findByIdAndKeycloakId(dto.idEndereco(), keycloakId)
                    .orElseThrow(() -> new WebApplicationException("Endereco nao encontrado", Status.NOT_FOUND))
            );
        }

        List<ItemPedido> itens = new ArrayList<>();
        for (ItemPedidoRequestDTO itemDTO : dto.itens()) {
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setQuantidade(itemDTO.quantidade());

            if (itemDTO.idViolao() != null) {
                Violao violao = violaoRepository.findById(itemDTO.idViolao());
                if (violao == null)
                    throw new WebApplicationException("Violao nao encontrado", Status.NOT_FOUND);
                if (violao.getQuantidadeEstoque() < itemDTO.quantidade())
                    throw new WebApplicationException(
                        "Estoque insuficiente para: " + violao.getNome(), Status.BAD_REQUEST);
                violao.setQuantidadeEstoque(violao.getQuantidadeEstoque() - itemDTO.quantidade());
                item.setViolao(violao);
                item.setPrecoUnitario(violao.getPrecoBase());

            } else if (itemDTO.idAcessorio() != null) {
                Acessorio acessorio = acessorioRepository.findById(itemDTO.idAcessorio());
                if (acessorio == null)
                    throw new WebApplicationException("Acessorio nao encontrado", Status.NOT_FOUND);
                if (acessorio.getQuantidadeEstoque() < itemDTO.quantidade())
                    throw new WebApplicationException(
                        "Estoque insuficiente para: " + acessorio.getNome(), Status.BAD_REQUEST);
                acessorio.setQuantidadeEstoque(acessorio.getQuantidadeEstoque() - itemDTO.quantidade());
                item.setAcessorio(acessorio);
                item.setPrecoUnitario(acessorio.getPrecoUnitario());

            } else {
                throw new WebApplicationException(
                    "Cada item deve ter idViolao ou idAcessorio", Status.BAD_REQUEST);
            }

            itens.add(item);
        }

        pedido.setItens(itens);
        pedidoRepository.persist(pedido);
        return PedidoMapper.toResponseDTO(pedido);
    }

    @Override
    public List<PedidoResponseDTO> findByKeycloakId(String keycloakId) {
        return pedidoRepository.findByKeycloakId(keycloakId).stream()
                .map(PedidoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public PedidoResponseDTO findByIdAndKeycloakId(Long id, String keycloakId) {
        return pedidoRepository.findByIdAndKeycloakId(id, keycloakId)
                .map(PedidoMapper::toResponseDTO)
                .orElseThrow(() -> new WebApplicationException("Pedido nao encontrado", Status.NOT_FOUND));
    }
}
