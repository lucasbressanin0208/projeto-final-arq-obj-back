package br.niaga.servija;

import br.niaga.servija.models.CategoriaServico;
import br.niaga.servija.repository.CategoriaServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final CategoriaServicoRepository categoriaServicoRepository;

    @Override
    public void run(ApplicationArguments args) {
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
