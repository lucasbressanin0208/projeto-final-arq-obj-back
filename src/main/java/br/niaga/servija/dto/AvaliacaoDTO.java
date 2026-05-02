package br.niaga.servija.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvaliacaoDTO {

    private UUID clienteId;
    private UUID prestadorId;
    private UUID agendamentoId;
    private Integer nota;
    private String comentario;
}