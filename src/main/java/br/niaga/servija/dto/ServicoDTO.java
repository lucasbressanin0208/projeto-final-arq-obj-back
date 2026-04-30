package br.niaga.servija.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoDTO {

    private String nome;
    private String descricao;
    private Double preco;
    private Integer duracaoMinutos;
    private UUID categoriaId;
    private UUID prestadorId;
    private Boolean ativo;
}