package br.niaga.servija.dto.save;

import br.niaga.servija.models.Disponibilidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
public class SaveDisponibilidadeDTO {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Boolean ativa;

    public Disponibilidade toModel() {
        Disponibilidade d = new Disponibilidade();
        d.setDiaSemana(this.diaSemana);
        d.setHoraInicio(this.horaInicio);
        d.setHoraFim(this.horaFim);
        d.setAtiva(this.ativa);
        return d;
    }
}