package br.niaga.servija.repository;

import br.niaga.servija.models.Disponibilidade;
import br.niaga.servija.models.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, UUID> {

    // Buscar todas de um prestador
    List<Disponibilidade> findByPrestador(Prestador prestador);

    // Buscar por prestador e dia da semana
    List<Disponibilidade> findByPrestadorAndDiaSemana(Prestador prestador, DayOfWeek diaSemana);

    // Buscar por prestador, dia e horário
    List<Disponibilidade> findByPrestadorAndDiaSemanaAndHoraInicioLessThanAndHoraFimGreaterThan(
            Prestador prestador,
            DayOfWeek diaSemana,
            LocalTime horaFim,
            LocalTime horaInicio
    );
}