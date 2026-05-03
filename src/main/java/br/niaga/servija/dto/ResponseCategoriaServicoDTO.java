package br.niaga.servija.dto;

import br.niaga.servija.models.CategoriaServico;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseCategoriaServicoDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private Boolean ativa;

    public static ResponseCategoriaServicoDTO toDTO(CategoriaServico c) {
        ResponseCategoriaServicoDTO dto = new ResponseCategoriaServicoDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setDescricao(c.getDescricao());
        dto.setAtiva(c.getAtiva());
        return dto;
    }
}
