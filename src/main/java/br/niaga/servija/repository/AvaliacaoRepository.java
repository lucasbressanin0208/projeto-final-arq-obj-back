package br.niaga.servija.repository;

import br.niaga.servija.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
    boolean existsByAgendamentoId(UUID agendamentoId);
}