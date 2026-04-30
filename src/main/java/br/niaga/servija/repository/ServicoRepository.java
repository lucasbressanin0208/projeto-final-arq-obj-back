package br.niaga.servija.repository;

import br.niaga.servija.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, UUID> {

    List<Servico> findAllByAtivoTrue();

    List<Servico> findAllByPrestadorId(UUID prestadorId);

    List<Servico> findAllByCategoriaId(UUID categoriaId);

    List<Servico> findAllByPrestadorIdAndAtivoTrue(UUID prestadorId);

    boolean existsByNomeAndPrestadorId(String nome, UUID prestadorId);
}
