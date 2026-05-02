package br.niaga.servija.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "agendamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "prestador_id", nullable = false)
    private Prestador prestador;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(nullable = false)
    private LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento status;

    private String observacaoCliente;

    public void confirmar() {
        if (this.status != StatusAgendamento.PENDENTE) {
            throw new IllegalStateException("Apenas agendamentos pendentes podem ser confirmados");
        }

        this.status = StatusAgendamento.CONFIRMADO;
    }

    public void recusar() {
        if (this.status != StatusAgendamento.PENDENTE) {
            throw new IllegalStateException("Apenas agendamentos pendentes podem ser recusados");
        }

        this.status = StatusAgendamento.RECUSADO;
    }

    public void cancelar() {
        if (this.status == StatusAgendamento.CONCLUIDO) {
            throw new IllegalStateException("Agendamento concluído não pode ser cancelado");
        }

        if (this.status == StatusAgendamento.RECUSADO) {
            throw new IllegalStateException("Agendamento recusado não pode ser cancelado");
        }

        this.status = StatusAgendamento.CANCELADO;
    }

    public void concluir() {
        if (this.status != StatusAgendamento.CONFIRMADO) {
            throw new IllegalStateException("Apenas agendamentos confirmados podem ser concluídos");
        }

        this.status = StatusAgendamento.CONCLUIDO;
    }

    public boolean estaConcluido() {
        return this.status == StatusAgendamento.CONCLUIDO;
    }

    public boolean estaConfirmado() {
        return this.status == StatusAgendamento.CONFIRMADO;
    }

    public boolean estaPendente() {
        return this.status == StatusAgendamento.PENDENTE;
    }
}