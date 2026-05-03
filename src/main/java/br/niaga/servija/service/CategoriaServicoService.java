package br.niaga.servija.service;

import br.niaga.servija.dto.edit.EditCategoriaServicoDTO;
import br.niaga.servija.dto.response.ResponseCategoriaServicoDTO;
import br.niaga.servija.dto.save.SaveCategoriaServicoDTO;
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

    public ResponseCategoriaServicoDTO criar(SaveCategoriaServicoDTO dto) {
        if (categoriaServicoRepository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com esse nome");
        }
        CategoriaServico categoria = dto.toModel();
        return ResponseCategoriaServicoDTO.toDTO(categoriaServicoRepository.save(categoria));
    }

    public List<ResponseCategoriaServicoDTO> listarTodas() {
        return categoriaServicoRepository.findAll().stream()
                .map(ResponseCategoriaServicoDTO::toDTO)
                .toList();
    }

    public List<ResponseCategoriaServicoDTO> listarAtivas() {
        return categoriaServicoRepository.findAllByAtivaTrue().stream()
                .map(ResponseCategoriaServicoDTO::toDTO)
                .toList();
    }

    public ResponseCategoriaServicoDTO buscarPorId(UUID id) {
        return ResponseCategoriaServicoDTO.toDTO(buscarOuFalhar(id));
    }

    public ResponseCategoriaServicoDTO atualizar(UUID id, EditCategoriaServicoDTO dto) {
        CategoriaServico categoria = buscarOuFalhar(id);
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return ResponseCategoriaServicoDTO.toDTO(categoriaServicoRepository.save(categoria));
    }

    public void ativar(UUID id) {
        CategoriaServico categoria = buscarOuFalhar(id);
        categoria.setAtiva(true);
        categoriaServicoRepository.save(categoria);
    }

    public void desativar(UUID id) {
        CategoriaServico categoria = buscarOuFalhar(id);
        categoria.setAtiva(false);
        categoriaServicoRepository.save(categoria);
    }

    private CategoriaServico buscarOuFalhar(UUID id) {
        return categoriaServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }
}
