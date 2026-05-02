package br.niaga.servija.service;

import br.niaga.servija.dto.AgendamentoDTO;
import br.niaga.servija.models.*;
import br.niaga.servija.repository.AgendamentoRepository;
import br.niaga.servija.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final PagamentoService pagamentoService;

    public Agendamento criar(AgendamentoDTO dto) {
        validarDadosObrigatorios(dto);

        Servico servico = servicoRepository.findById(dto.getServicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        if (!Boolean.TRUE.equals(servico.getAtivo())) {
            throw new IllegalArgumentException("Não é possível agendar um serviço inativo");
        }

        if (dto.getDataHoraInicio().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível criar agendamento no passado");
        }

        Prestador prestador = servico.getPrestador();

        if (prestador == null || prestador.getId() == null) {
            throw new IllegalStateException("O serviço não possui prestador vinculado");
        }

        if (dto.getPrestadorId() != null && !dto.getPrestadorId().equals(prestador.getId())) {
            throw new IllegalArgumentException("O serviço informado não pertence ao prestador informado");
        }

        LocalDateTime dataHoraFim = servico.calcularHorarioFim(dto.getDataHoraInicio());

        boolean existeConflito = agendamentoRepository
                .existsByPrestadorIdAndStatusAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
                        prestador.getId(),
                        StatusAgendamento.CONFIRMADO,
                        dataHoraFim,
                        dto.getDataHoraInicio()
                );

        if (existeConflito) {
            throw new IllegalArgumentException("Prestador já possui agendamento confirmado nesse horário");
        }

        Cliente cliente = new Cliente();
        cliente.setId(dto.getClienteId());

        Agendamento agendamento = Agendamento.builder()
                .cliente(cliente)
                .prestador(prestador)
                .servico(servico)
                .dataHoraInicio(dto.getDataHoraInicio())
                .dataHoraFim(dataHoraFim)
                .status(StatusAgendamento.PENDENTE)
                .observacaoCliente(dto.getObservacaoCliente())
                .build();

        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        pagamentoService.criarPagamentoPendente(
                agendamentoSalvo,
                dto.getMetodoPagamento()
        );

        return agendamentoSalvo;
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> listarPorCliente(UUID clienteId) {
        return agendamentoRepository.findAllByClienteId(clienteId);
    }

    public List<Agendamento> listarPorPrestador(UUID prestadorId) {
        return agendamentoRepository.findAllByPrestadorId(prestadorId);
    }

    public List<Agendamento> listarPorPrestadorEStatus(UUID prestadorId, StatusAgendamento status) {
        return agendamentoRepository.findAllByPrestadorIdAndStatus(prestadorId, status);
    }

    public Agendamento buscarPorId(UUID id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public Agendamento confirmar(UUID id) {
        Agendamento agendamento = buscarPorId(id);

        boolean existeConflito = agendamentoRepository
                .existsByPrestadorIdAndStatusAndDataHoraInicioLessThanAndDataHoraFimGreaterThanAndIdNot(
                        agendamento.getPrestador().getId(),
                        StatusAgendamento.CONFIRMADO,
                        agendamento.getDataHoraFim(),
                        agendamento.getDataHoraInicio(),
                        agendamento.getId()
                );

        if (existeConflito) {
            throw new IllegalArgumentException("Prestador já possui outro agendamento confirmado nesse horário");
        }

        agendamento.confirmar();

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento recusar(UUID id) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.recusar();

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento cancelar(UUID id) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.cancelar();

        pagamentoService.cancelarPagamentoPendenteDoAgendamento(agendamento.getId());

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento concluir(UUID id) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.concluir();

        return agendamentoRepository.save(agendamento);
    }

    private void validarDadosObrigatorios(AgendamentoDTO dto) {
        if (dto.getClienteId() == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }

        if (dto.getServicoId() == null) {
            throw new IllegalArgumentException("Serviço é obrigatório");
        }

        if (dto.getDataHoraInicio() == null) {
            throw new IllegalArgumentException("Data e horário de início são obrigatórios");
        }
    }
}