package br.niaga.servija.dto;

import br.niaga.servija.models.MetodoPagamento;
import br.niaga.servija.models.Pagamento;
import br.niaga.servija.models.StatusPagamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponsePagamentoDTO {

    private UUID id;
    private UUID agendamentoId;
    private Double valor;
    private MetodoPagamento metodo;
    private StatusPagamento status;
    private LocalDateTime dataPagamento;

    public static ResponsePagamentoDTO toDTO(Pagamento p) {
        ResponsePagamentoDTO dto = new ResponsePagamentoDTO();
        dto.setId(p.getId());
        dto.setAgendamentoId(p.getAgendamento() != null ? p.getAgendamento().getId() : null);
        dto.setValor(p.getValor());
        dto.setMetodo(p.getMetodo());
        dto.setStatus(p.getStatus());
        dto.setDataPagamento(p.getDataPagamento());
        return dto;
    }
}
