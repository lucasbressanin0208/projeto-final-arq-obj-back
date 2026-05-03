package br.niaga.servija.dto.save;

import br.niaga.servija.models.MetodoPagamento;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SavePagamentoDTO {

    private UUID agendamentoId;
    private MetodoPagamento metodo;
}
