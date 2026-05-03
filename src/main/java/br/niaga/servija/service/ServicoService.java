package br.niaga.servija.service;

import br.niaga.servija.dto.edit.EditServicoDTO;
import br.niaga.servija.dto.response.ResponseServicoDTO;
import br.niaga.servija.dto.save.SaveServicoDTO;
import br.niaga.servija.models.CategoriaServico;
import br.niaga.servija.models.Prestador;
import br.niaga.servija.models.Servico;
import br.niaga.servija.repository.CategoriaServicoRepository;
import br.niaga.servija.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final CategoriaServicoRepository categoriaServicoRepository;

    public ResponseServicoDTO criar(SaveServicoDTO dto) {
        CategoriaServico categoria = categoriaServicoRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!categoria.getAtiva()) {
            throw new IllegalArgumentException("Não é possível criar serviço em categoria inativa");
        }

        if (servicoRepository.existsByNomeAndPrestadorId(dto.getNome(), dto.getPrestadorId())) {
            throw new IllegalArgumentException("Prestador já possui um serviço com esse nome");
        }

        if (dto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }

        if (dto.getDuracaoMinutos() <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }

        Prestador prestador = Prestador.builder().id(dto.getPrestadorId()).build();

        Servico servico = Servico.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .duracaoMinutos(dto.getDuracaoMinutos())
                .ativo(true)
                .categoria(categoria)
                .prestador(prestador)
                .build();

        return ResponseServicoDTO.toDTO(servicoRepository.save(servico));
    }

    public List<ResponseServicoDTO> listarPorPrestador(UUID prestadorId) {
        return servicoRepository.findAllByPrestadorId(prestadorId).stream()
                .map(ResponseServicoDTO::toDTO)
                .toList();
    }

    public List<ResponseServicoDTO> listarAtivosPorPrestador(UUID prestadorId) {
        return servicoRepository.findAllByPrestadorIdAndAtivoTrue(prestadorId).stream()
                .map(ResponseServicoDTO::toDTO)
                .toList();
    }

    public List<ResponseServicoDTO> listarPorCategoria(UUID categoriaId) {
        return servicoRepository.findAllByCategoriaId(categoriaId).stream()
                .map(ResponseServicoDTO::toDTO)
                .toList();
    }

    public ResponseServicoDTO buscarPorId(UUID id) {
        return ResponseServicoDTO.toDTO(buscarOuFalhar(id));
    }

    public ResponseServicoDTO atualizar(UUID id, EditServicoDTO dto) {
        Servico servico = buscarOuFalhar(id);
        servico.setNome(dto.getNome());
        servico.setDescricao(dto.getDescricao());
        servico.setPreco(dto.getPreco());
        servico.setDuracaoMinutos(dto.getDuracaoMinutos());
        return ResponseServicoDTO.toDTO(servicoRepository.save(servico));
    }

    public void ativar(UUID id) {
        Servico servico = buscarOuFalhar(id);
        servico.ativar();
        servicoRepository.save(servico);
    }

    public void desativar(UUID id) {
        Servico servico = buscarOuFalhar(id);
        servico.desativar();
        servicoRepository.save(servico);
    }

    private Servico buscarOuFalhar(UUID id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }
}
