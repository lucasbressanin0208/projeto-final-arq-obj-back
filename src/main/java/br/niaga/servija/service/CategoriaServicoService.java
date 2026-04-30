package br.niaga.servija.service;

import br.niaga.servija.dto.CategoriaServicoDTO;
import br.niaga.servija.models.CategoriaServico;
import br.niaga.servija.repository.CategoriaServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaServicoService {

    private final CategoriaServicoRepository categoriaServicoRepository;

    public CategoriaServico criar(CategoriaServicoDTO dto) {
        if (categoriaServicoRepository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com esse nome");
        }

        CategoriaServico categoria = CategoriaServico.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .ativa(dto.getAtiva())
                .build();

        return categoriaServicoRepository.save(categoria);
    }

    public List<CategoriaServico> listarTodas() {
        return categoriaServicoRepository.findAll();
    }

    public List<CategoriaServico> listarAtivas() {
        return categoriaServicoRepository.findAllByAtivaTrue();
    }

    public CategoriaServico buscarPorId(UUID id) {
        return categoriaServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public CategoriaServico atualizar(UUID id, CategoriaServicoDTO dto) {
        CategoriaServico categoria = buscarPorId(id);

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        return categoriaServicoRepository.save(categoria);
    }

    public void ativar(UUID id) {
        CategoriaServico categoria = buscarPorId(id);
        categoria.setAtiva(true);
        categoriaServicoRepository.save(categoria);
    }

    public void desativar(UUID id) {
        CategoriaServico categoria = buscarPorId(id);
        categoria.setAtiva(false);
        categoriaServicoRepository.save(categoria);
    }
}
