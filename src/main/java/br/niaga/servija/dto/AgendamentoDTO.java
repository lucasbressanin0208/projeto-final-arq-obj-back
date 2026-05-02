package br.niaga.servija.dto;

import br.niaga.servija.models.MetodoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoDTO {

    private UUID clienteId;
    private UUID prestadorId;
    private UUID servicoId;
    private LocalDateTime dataHoraInicio;
    private String observacaoCliente;
    private MetodoPagamento metodoPagamento;
}