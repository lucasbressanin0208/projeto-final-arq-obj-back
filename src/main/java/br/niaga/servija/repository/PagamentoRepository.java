package br.niaga.servija.repository;

import br.niaga.servija.models.Pagamento;
import br.niaga.servija.models.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

    Optional<Pagamento> findByAgendamentoId(UUID agendamentoId);

    boolean existsByAgendamentoId(UUID agendamentoId);

    List<Pagamento> findAllByStatus(StatusPagamento status);
}