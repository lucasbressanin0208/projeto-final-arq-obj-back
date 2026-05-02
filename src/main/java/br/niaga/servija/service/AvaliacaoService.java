package br.niaga.servija.service;

import br.niaga.servija.dto.AvaliacaoDTO;
import br.niaga.servija.models.*;
import br.niaga.servija.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final ClienteRepository clienteRepository;
    private final PrestadorRepository prestadorRepository;
    private final AgendamentoRepository agendamentoRepository;

    public Avaliacao criar(AvaliacaoDTO dto) {

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

        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listarTodos() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao buscarPorId(UUID id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
    }

    public void deletar(UUID id) {
        Avaliacao avaliacao = buscarPorId(id);
        avaliacaoRepository.delete(avaliacao);
    }
}