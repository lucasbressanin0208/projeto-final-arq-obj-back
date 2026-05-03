package br.niaga.servija.dto.response;

import br.niaga.servija.models.Servico;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseServicoDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer duracaoMinutos;
    private Boolean ativo;
    private UUID categoriaId;
    private UUID prestadorId;

    public static ResponseServicoDTO toDTO(Servico s) {
        ResponseServicoDTO dto = new ResponseServicoDTO();
        dto.setId(s.getId());
        dto.setNome(s.getNome());
        dto.setDescricao(s.getDescricao());
        dto.setPreco(s.getPreco());
        dto.setDuracaoMinutos(s.getDuracaoMinutos());
        dto.setAtivo(s.getAtivo());
        dto.setCategoriaId(s.getCategoria() != null ? s.getCategoria().getId() : null);
        dto.setPrestadorId(s.getPrestador() != null ? s.getPrestador().getId() : null);
        return dto;
    }
}
