package br.niaga.servija.repository;

import br.niaga.servija.models.CategoriaServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaServicoRepository extends JpaRepository<CategoriaServico, UUID> {

    Optional<CategoriaServico> findByNomeIgnoreCase(String nome);

    List<CategoriaServico> findAllByAtivaTrue();

    boolean existsByNomeIgnoreCase(String nome);
}
