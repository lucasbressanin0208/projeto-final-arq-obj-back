package br.niaga.servija.dto;

import br.niaga.servija.models.Avaliacao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseAvaliacaoDTO {

    private UUID id;
    private UUID clienteId;
    private UUID prestadorId;
    private UUID agendamentoId;
    private Integer nota;
    private String comentario;
    private LocalDateTime dataCriacao;

    public static ResponseAvaliacaoDTO toDTO(Avaliacao a) {
        ResponseAvaliacaoDTO dto = new ResponseAvaliacaoDTO();
        dto.setId(a.getId());
        dto.setClienteId(a.getCliente() != null ? a.getCliente().getId() : null);
        dto.setPrestadorId(a.getPrestador() != null ? a.getPrestador().getId() : null);
        dto.setAgendamentoId(a.getAgendamento() != null ? a.getAgendamento().getId() : null);
        dto.setNota(a.getNota());
        dto.setComentario(a.getComentario());
        dto.setDataCriacao(a.getDataCriacao());
        return dto;
    }
}
