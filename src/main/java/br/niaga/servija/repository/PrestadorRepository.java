package br.niaga.servija.repository;

import br.niaga.servija.models.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, UUID> {

    boolean existsByEmail(String email);

    Optional<Prestador> findByEmail(String email);

    List<Prestador> findAllByAtivoTrue();

    List<Prestador> findAllByEnderecoCidadeIgnoreCaseAndAtivoTrue(String cidade);

    List<Prestador> findAllByEnderecoCidadeIgnoreCaseAndEnderecoBairroIgnoreCaseAndAtivoTrue(String cidade, String bairro);
}