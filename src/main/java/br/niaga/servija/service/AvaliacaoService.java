package br.niaga.servija.service;

import br.niaga.servija.dto.ResponseAvaliacaoDTO;
import br.niaga.servija.dto.SaveAvaliacaoDTO;
import br.niaga.servija.models.*;
import br.niaga.servija.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final ClienteRepository clienteRepository;
    private final PrestadorRepository prestadorRepository;
    private final AgendamentoRepository agendamentoRepository;

    public ResponseAvaliacaoDTO criar(SaveAvaliacaoDTO dto) {
        if (avaliacaoRepository.existsByAgendamentoId(dto.getAgendamentoId())) {
            throw new IllegalArgumentException("Agendamento já possui avaliação");
        }

        if (dto.getNota() < 1 || dto.getNota() > 5) {
            throw new IllegalArgumentException("Nota deve ser entre 1 e 5");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Prestador prestador = prestadorRepository.findById(dto.getPrestadorId())
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));

        Agendamento agendamento = agendamentoRepository.findById(dto.getAgendamentoId())
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Avaliacao avaliacao = Avaliacao.builder()
                .cliente(cliente)
                .prestador(prestador)
                .agendamento(agendamento)
                .nota(dto.getNota())
                .comentario(dto.getComentario())
                .dataCriacao(LocalDateTime.now())
                .build();

        return ResponseAvaliacaoDTO.toDTO(avaliacaoRepository.save(avaliacao));
    }

    public List<ResponseAvaliacaoDTO> listarTodos() {
        return avaliacaoRepository.findAll().stream()
                .map(ResponseAvaliacaoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ResponseAvaliacaoDTO buscarPorId(UUID id) {
        return ResponseAvaliacaoDTO.toDTO(buscarOuFalhar(id));
    }

    public void deletar(UUID id) {
        avaliacaoRepository.delete(buscarOuFalhar(id));
    }

    private Avaliacao buscarOuFalhar(UUID id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
    }
}
