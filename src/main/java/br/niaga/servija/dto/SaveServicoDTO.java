package br.niaga.servija.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveServicoDTO {

    private String nome;
    private String descricao;
    private Double preco;
    private Integer duracaoMinutos;
    private UUID categoriaId;
    private UUID prestadorId;
}
