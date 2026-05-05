package br.niaga.servija.dto.response;

import br.niaga.servija.models.Disponibilidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseDisponibilidadeDTO {

    private UUID id;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Boolean ativa;

    public static ResponseDisponibilidadeDTO toDTO(Disponibilidade d) {
        ResponseDisponibilidadeDTO dto = new ResponseDisponibilidadeDTO();
        dto.setId(d.getId());
        dto.setDiaSemana(d.getDiaSemana());
        dto.setHoraInicio(d.getHoraInicio());
        dto.setHoraFim(d.getHoraFim());
        dto.setAtiva(d.getAtiva());
        return dto;
    }
}