package br.niaga.servija.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "disponibilidades")
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "prestador_id", nullable = false)
    private Prestador prestador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek diaSemana;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFim;

    @Column(nullable = false)
    private Boolean ativa;

    public boolean horarioValido() {
        return horaFim.isAfter(horaInicio);
    }

    public boolean contemHorario(LocalDateTime dataHora, int duracaoMinutos) {
        LocalTime inicio = dataHora.toLocalTime();
        LocalTime fim = inicio.plusMinutes(duracaoMinutos);

        return !inicio.isBefore(horaInicio) && !fim.isAfter(horaFim);
    }

    public boolean conflitaCom(Disponibilidade outra) {
        if (!this.diaSemana.equals(outra.diaSemana)) return false;

        return this.horaInicio.isBefore(outra.horaFim) &&
                outra.horaInicio.isBefore(this.horaFim);
    }
}