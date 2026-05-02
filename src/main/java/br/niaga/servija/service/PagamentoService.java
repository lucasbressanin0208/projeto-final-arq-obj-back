package br.niaga.servija.service;

import br.niaga.servija.dto.PagamentoDTO;
import br.niaga.servija.models.*;
import br.niaga.servija.repository.AgendamentoRepository;
import br.niaga.servija.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AgendamentoRepository agendamentoRepository;

    public Pagamento criar(PagamentoDTO dto) {
        if (dto.getAgendamentoId() == null) {
            throw new IllegalArgumentException("Agendamento é obrigatório");
        }

        Agendamento agendamento = agendamentoRepository.findById(dto.getAgendamentoId())
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        return criarPagamentoPendente(agendamento, dto.getMetodo());
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

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public List<Pagamento> listarPorStatus(StatusPagamento status) {
        return pagamentoRepository.findAllByStatus(status);
    }

    public Pagamento buscarPorId(UUID id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public Pagamento buscarPorAgendamento(UUID agendamentoId) {
        return pagamentoRepository.findByAgendamentoId(agendamentoId)
                .orElseThrow(() -> new RuntimeException("Pagamento do agendamento não encontrado"));
    }

    public Pagamento marcarComoPago(UUID id) {
        Pagamento pagamento = buscarPorId(id);
        pagamento.marcarComoPago();

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento cancelar(UUID id) {
        Pagamento pagamento = buscarPorId(id);
        pagamento.cancelar();

        return pagamentoRepository.save(pagamento);
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
}