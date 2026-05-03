package br.niaga.servija.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveAvaliacaoDTO {

    private UUID clienteId;
    private UUID prestadorId;
    private UUID agendamentoId;
    private Integer nota;
    private String comentario;
}
