package br.unitins.ecommerce.endereco.mapper;

import br.unitins.ecommerce.endereco.dto.EnderecoRequestDTO;
import br.unitins.ecommerce.endereco.dto.EnderecoResponseDTO;
import br.unitins.ecommerce.endereco.model.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequestDTO dto) {
        Endereco e = new Endereco();
        e.setCep(dto.cep());
        e.setRua(dto.rua());
        e.setNumero(dto.numero());
        e.setComplemento(dto.complemento());
        e.setBairro(dto.bairro());
        e.setCidade(dto.cidade());
        e.setEstado(dto.estado());
        return e;
    }

    public static EnderecoResponseDTO toResponseDTO(Endereco e) {
        return new EnderecoResponseDTO(
                e.getId(),
                e.getCep(),
                e.getRua(),
                e.getNumero(),
                e.getComplemento(),
                e.getBairro(),
                e.getCidade(),
                e.getEstado()
        );
    }
}
