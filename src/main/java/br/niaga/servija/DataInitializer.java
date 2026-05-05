package br.niaga.servija;

import br.niaga.servija.models.CategoriaServico;
import br.niaga.servija.repository.CategoriaServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final CategoriaServicoRepository categoriaServicoRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        // Remove stale FK constraint that references table "endereco" (wrong name).
        // The Endereco entity uses table "enderecos", but an old constraint was created
        // before the table was renamed. This causes FK violations on every prestador INSERT.
        jdbcTemplate.execute(
            "ALTER TABLE prestadores DROP CONSTRAINT IF EXISTS fkt1bcw9q4ydx3dwf7rwofk2x8r"
        );

        // Seed categories
        if (categoriaServicoRepository.count() > 0) return;

        List<CategoriaServico> categorias = List.of(
            CategoriaServico.builder().nome("Cabeleireiro").descricao("Corte, coloração e cuidados com o cabelo").ativa(true).build(),
            CategoriaServico.builder().nome("Manicure").descricao("Unhas, cutículas e nail art").ativa(true).build(),
            CategoriaServico.builder().nome("Eletricista").descricao("Instalações e reparos elétricos").ativa(true).build(),
            CategoriaServico.builder().nome("Encanador").descricao("Instalações hidráulicas e reparos").ativa(true).build(),
            CategoriaServico.builder().nome("Limpeza").descricao("Limpeza residencial e comercial").ativa(true).build(),
            CategoriaServico.builder().nome("Pet").descricao("Banho, tosa e cuidados com animais").ativa(true).build(),
            CategoriaServico.builder().nome("Estética").descricao("Tratamentos estéticos e beleza").ativa(true).build(),
            CategoriaServico.builder().nome("Marceneiro").descricao("Móveis, carpintaria e madeira").ativa(true).build()
        );

        categoriaServicoRepository.saveAll(categorias);
    }
}
