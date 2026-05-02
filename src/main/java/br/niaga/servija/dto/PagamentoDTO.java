package br.niaga.servija.dto;

import br.niaga.servija.models.MetodoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoDTO {

    private UUID agendamentoId;
    private MetodoPagamento metodo;
}