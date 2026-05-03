package br.niaga.servija.dto.response;

import br.niaga.servija.models.Agendamento;
import br.niaga.servija.models.StatusAgendamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseAgendamentoDTO {

    private UUID id;
    private UUID clienteId;
    private UUID prestadorId;
    private UUID servicoId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendamento status;
    private String observacaoCliente;

    public static ResponseAgendamentoDTO toDTO(Agendamento a) {
        ResponseAgendamentoDTO dto = new ResponseAgendamentoDTO();
        dto.setId(a.getId());
        dto.setClienteId(a.getCliente() != null ? a.getCliente().getId() : null);
        dto.setPrestadorId(a.getPrestador() != null ? a.getPrestador().getId() : null);
        dto.setServicoId(a.getServico() != null ? a.getServico().getId() : null);
        dto.setDataHoraInicio(a.getDataHoraInicio());
        dto.setDataHoraFim(a.getDataHoraFim());
        dto.setStatus(a.getStatus());
        dto.setObservacaoCliente(a.getObservacaoCliente());
        return dto;
    }
}
