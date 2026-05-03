package br.niaga.servija.service;

import br.niaga.servija.dto.ResponsePagamentoDTO;
import br.niaga.servija.dto.SavePagamentoDTO;
import br.niaga.servija.models.*;
import br.niaga.servija.repository.AgendamentoRepository;
import br.niaga.servija.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AgendamentoRepository agendamentoRepository;

    public ResponsePagamentoDTO criar(SavePagamentoDTO dto) {
        if (dto.getAgendamentoId() == null) {
            throw new IllegalArgumentException("Agendamento é obrigatório");
        }
        Agendamento agendamento = agendamentoRepository.findById(dto.getAgendamentoId())
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return ResponsePagamentoDTO.toDTO(criarPagamentoPendente(agendamento, dto.getMetodo()));
    }

    public Pagamento criarPagamentoPendente(Agendamento agendamento, MetodoPagamento metodo) {
        if (pagamentoRepository.existsByAgendamentoId(agendamento.getId())) {
            throw new IllegalArgumentException("Esse agendamento já possui pagamento");
        }

        if (agendamento.getServico() == null) {
            throw new IllegalStateException("Agendamento não possui serviço vinculado");
        }

        MetodoPagamento metodoPagamento = metodo != null ? metodo : MetodoPagamento.PIX;

        Pagamento pagamento = Pagamento.builder()
                .agendamento(agendamento)
                .valor(agendamento.getServico().getPreco())
                .metodo(metodoPagamento)
                .status(StatusPagamento.PENDENTE)
                .dataPagamento(null)
                .build();

        return pagamentoRepository.save(pagamento);
    }

    public List<ResponsePagamentoDTO> listarTodos() {
        return pagamentoRepository.findAll().stream()
                .map(ResponsePagamentoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<ResponsePagamentoDTO> listarPorStatus(StatusPagamento status) {
        return pagamentoRepository.findAllByStatus(status).stream()
                .map(ResponsePagamentoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ResponsePagamentoDTO buscarPorId(UUID id) {
        return ResponsePagamentoDTO.toDTO(buscarOuFalhar(id));
    }

    public ResponsePagamentoDTO buscarPorAgendamento(UUID agendamentoId) {
        return ResponsePagamentoDTO.toDTO(
                pagamentoRepository.findByAgendamentoId(agendamentoId)
                        .orElseThrow(() -> new RuntimeException("Pagamento do agendamento não encontrado"))
        );
    }

    public ResponsePagamentoDTO marcarComoPago(UUID id) {
        Pagamento pagamento = buscarOuFalhar(id);
        pagamento.marcarComoPago();
        return ResponsePagamentoDTO.toDTO(pagamentoRepository.save(pagamento));
    }

    public ResponsePagamentoDTO cancelar(UUID id) {
        Pagamento pagamento = buscarOuFalhar(id);
        pagamento.cancelar();
        return ResponsePagamentoDTO.toDTO(pagamentoRepository.save(pagamento));
    }

    public void cancelarPagamentoPendenteDoAgendamento(UUID agendamentoId) {
        pagamentoRepository.findByAgendamentoId(agendamentoId)
                .ifPresent(pagamento -> {
                    if (pagamento.estaPendente()) {
                        pagamento.cancelar();
                        pagamentoRepository.save(pagamento);
                    }
                });
    }

    private Pagamento buscarOuFalhar(UUID id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }
}
