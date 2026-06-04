package br.unitins.ecommerce.violao.mapper;

import br.unitins.ecommerce.violao.dto.ViolaoProdutoResponseDTO;
import br.unitins.ecommerce.violao.model.Violao;
import br.unitins.ecommerce.violao.model.ViolaoAco;
import br.unitins.ecommerce.violao.model.ViolaoNylon;

public class ViolaoProdutoMapper {

    public static ViolaoProdutoResponseDTO toResponseDTO(Violao v) {
        String tipo;
        String tipoCordas;

        if (v instanceof ViolaoAco aco) {
            tipo = "ACO";
            tipoCordas = aco.getTipoCordasAco().name();
        } else {
            tipo = "NYLON";
            tipoCordas = ((ViolaoNylon) v).getTipoCordasNylon().name();
        }

        return new ViolaoProdutoResponseDTO(
                v.getId(),
                v.getNome(),
                v.getPrecoBase(),
                v.getAnoFabricacao(),
                v.getMarca() != null ? v.getMarca().getNome() : null,
                v.getMadeira() != null ? v.getMadeira().getTipo() : null,
                tipo,
                tipoCordas,
                v.getQuantidadeEstoque()
        );
    }
}
