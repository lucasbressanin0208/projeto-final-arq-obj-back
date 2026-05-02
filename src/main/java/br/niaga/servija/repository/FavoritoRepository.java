package br.niaga.servija.repository;

import br.niaga.servija.models.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, UUID> {

    boolean existsByClienteIdAndPrestadorId(UUID clienteId, UUID prestadorId);
}