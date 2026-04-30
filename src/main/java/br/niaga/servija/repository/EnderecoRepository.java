package br.niaga.servija.repository;

import br.niaga.servija.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    // Buscar por cidade
    List<Endereco> findByCidadeIgnoreCase(String cidade);

    // Buscar por cidade e bairro
    List<Endereco> findByCidadeIgnoreCaseAndBairroIgnoreCase(String cidade, String bairro);
}