package br.niaga.servija.dto.edit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditServicoDTO {

    private String nome;
    private String descricao;
    private Double preco;
    private Integer duracaoMinutos;
}
