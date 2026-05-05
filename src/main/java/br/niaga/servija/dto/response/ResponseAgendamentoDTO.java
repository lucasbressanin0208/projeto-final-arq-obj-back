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
    private String clienteNome;
    private UUID prestadorId;
    private String prestadorNome;
    private UUID servicoId;
    private String servicoNome;
    private Double servicoPreco;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendamento status;
    private String observacaoCliente;

    public static ResponseAgendamentoDTO toDTO(Agendamento a) {
        ResponseAgendamentoDTO dto = new ResponseAgendamentoDTO();
        dto.setId(a.getId());
        if (a.getCliente() != null) {
            dto.setClienteId(a.getCliente().getId());
            dto.setClienteNome(a.getCliente().getNome());
        }
        if (a.getPrestador() != null) {
            dto.setPrestadorId(a.getPrestador().getId());
            dto.setPrestadorNome(a.getPrestador().getNome());
        }
        if (a.getServico() != null) {
            dto.setServicoId(a.getServico().getId());
            dto.setServicoNome(a.getServico().getNome());
            dto.setServicoPreco(a.getServico().getPreco());
        }
        dto.setDataHoraInicio(a.getDataHoraInicio());
        dto.setDataHoraFim(a.getDataHoraFim());
        dto.setStatus(a.getStatus());
        dto.setObservacaoCliente(a.getObservacaoCliente());
        return dto;
    }
}
