package br.unitins.ecommerce.acessorio.mapper;

import br.unitins.ecommerce.acessorio.dto.AcessorioRequestDTO;
import br.unitins.ecommerce.acessorio.dto.AcessorioResponseDTO;
import br.unitins.ecommerce.acessorio.model.Acessorio;

public class AcessorioMapper {

    public static Acessorio toEntity(AcessorioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Acessorio acessorio = new Acessorio();
        acessorio.setNome(dto.nome());
        acessorio.setDescricao(dto.descricao());
        acessorio.setQuantidadeEstoque(dto.quantidadeEstoque());
        acessorio.setPrecoUnitario(dto.precoUnitario());

        return acessorio;
    }

    public static AcessorioResponseDTO toResponse(Acessorio acessorio) {
        if (acessorio == null) {
            return null;
        }

        return new AcessorioResponseDTO(
            acessorio.getId(),
            acessorio.getNome(),
            acessorio.getDescricao(),
            acessorio.getQuantidadeEstoque(),
            acessorio.getPrecoUnitario()
        );
    }
}