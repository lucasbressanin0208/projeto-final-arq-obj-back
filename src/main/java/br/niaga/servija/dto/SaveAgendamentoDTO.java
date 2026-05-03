package br.niaga.servija.dto;

import br.niaga.servija.models.MetodoPagamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SaveAgendamentoDTO {

    private UUID clienteId;
    private UUID prestadorId;
    private UUID servicoId;
    private LocalDateTime dataHoraInicio;
    private String observacaoCliente;
    private MetodoPagamento metodoPagamento;
}
