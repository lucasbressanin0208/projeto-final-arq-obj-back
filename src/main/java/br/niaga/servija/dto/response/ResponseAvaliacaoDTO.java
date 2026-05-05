package br.niaga.servija.dto.response;

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
    private String clienteNome;
    private UUID prestadorId;
    private UUID agendamentoId;
    private Integer nota;
    private String comentario;
    private LocalDateTime dataCriacao;

    public static ResponseAvaliacaoDTO toDTO(Avaliacao a) {
        ResponseAvaliacaoDTO dto = new ResponseAvaliacaoDTO();
        dto.setId(a.getId());
        if (a.getCliente() != null) {
            dto.setClienteId(a.getCliente().getId());
            dto.setClienteNome(a.getCliente().getNome());
        }
        dto.setPrestadorId(a.getPrestador() != null ? a.getPrestador().getId() : null);
        dto.setAgendamentoId(a.getAgendamento() != null ? a.getAgendamento().getId() : null);
        dto.setNota(a.getNota());
        dto.setComentario(a.getComentario());
        dto.setDataCriacao(a.getDataCriacao());
        return dto;
    }
}
