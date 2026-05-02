package br.niaga.servija.repository;

import br.niaga.servija.models.Agendamento;
import br.niaga.servija.models.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {

    List<Agendamento> findAllByClienteId(UUID clienteId);

    List<Agendamento> findAllByPrestadorId(UUID prestadorId);

    List<Agendamento> findAllByPrestadorIdAndStatus(UUID prestadorId, StatusAgendamento status);

    boolean existsByPrestadorIdAndStatusAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
            UUID prestadorId,
            StatusAgendamento status,
            LocalDateTime dataHoraFim,
            LocalDateTime dataHoraInicio
    );

    boolean existsByPrestadorIdAndStatusAndDataHoraInicioLessThanAndDataHoraFimGreaterThanAndIdNot(
            UUID prestadorId,
            StatusAgendamento status,
            LocalDateTime dataHoraFim,
            LocalDateTime dataHoraInicio,
            UUID id
    );
}