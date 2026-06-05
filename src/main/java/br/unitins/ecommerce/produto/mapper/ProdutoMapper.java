package br.unitins.ecommerce.produto.mapper;

import br.unitins.ecommerce.acessorio.model.Acessorio;
import br.unitins.ecommerce.produto.dto.CategoriaProduto;
import br.unitins.ecommerce.produto.dto.ProdutoDetalheResponseDTO;
import br.unitins.ecommerce.produto.dto.ProdutoResponseDTO;
import br.unitins.ecommerce.violao.model.Violao;
import br.unitins.ecommerce.violao.model.ViolaoAco;
import br.unitins.ecommerce.violao.model.ViolaoNylon;

public class ProdutoMapper {

    public static ProdutoResponseDTO toResponseDTO(Violao v) {
        return new ProdutoResponseDTO(
                v.getId(),
                v.getNome(),
                v.getPrecoBase(),
                v.getMarca() != null ? v.getMarca().getNome() : null,
                CategoriaProduto.VIOLAO,
                v.getQuantidadeEstoque()
        );
    }

    public static ProdutoResponseDTO toResponseDTO(Acessorio a) {
        return new ProdutoResponseDTO(
                a.getId(),
                a.getNome(),
                a.getPrecoUnitario(),
                null,
                CategoriaProduto.ACESSORIO,
                a.getQuantidadeEstoque()
        );
    }

    public static ProdutoDetalheResponseDTO toDetalheDTO(Violao v) {
        String tipoViolao;
        String tipoCordas;
        if (v instanceof ViolaoAco aco) {
            tipoViolao = "ACO";
            tipoCordas = aco.getTipoCordasAco() != null ? aco.getTipoCordasAco().name() : null;
        } else if (v instanceof ViolaoNylon nylon) {
            tipoViolao = "NYLON";
            tipoCordas = nylon.getTipoCordasNylon() != null ? nylon.getTipoCordasNylon().name() : null;
        } else {
            tipoViolao = null;
            tipoCordas = null;
        }

        return new ProdutoDetalheResponseDTO(
                v.getId(),
                CategoriaProduto.VIOLAO,
                v.getNome(),
                null,
                v.getPrecoBase(),
                v.getQuantidadeEstoque(),
                v.getMarca() != null ? v.getMarca().getNome() : null,
                v.getAnoFabricacao(),
                v.getMadeira() != null ? v.getMadeira().getTipo() : null,
                tipoViolao,
                tipoCordas
        );
    }

    public static ProdutoDetalheResponseDTO toDetalheDTO(Acessorio a) {
        return new ProdutoDetalheResponseDTO(
                a.getId(),
                CategoriaProduto.ACESSORIO,
                a.getNome(),
                a.getDescricao(),
                a.getPrecoUnitario(),
                a.getQuantidadeEstoque(),
                null,
                null,
                null,
                null,
                null
        );
    }
}
