package br.niaga.servija.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "pagamentos",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "agendamento_id")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "agendamento_id", nullable = false)
    private Agendamento agendamento;

    @Column(nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPagamento metodo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;

    private LocalDateTime dataPagamento;

    public void marcarComoPago() {
        if (this.status == StatusPagamento.CANCELADO) {
            throw new IllegalStateException("Pagamento cancelado não pode ser marcado como pago");
        }

        if (this.status == StatusPagamento.REEMBOLSADO) {
            throw new IllegalStateException("Pagamento reembolsado não pode ser marcado como pago");
        }

        this.status = StatusPagamento.PAGO;
        this.dataPagamento = LocalDateTime.now();
    }

    public void cancelar() {
        if (this.status != StatusPagamento.PENDENTE) {
            throw new IllegalStateException("Apenas pagamentos pendentes podem ser cancelados");
        }

        this.status = StatusPagamento.CANCELADO;
    }

    public boolean estaPago() {
        return this.status == StatusPagamento.PAGO;
    }

    public boolean estaPendente() {
        return this.status == StatusPagamento.PENDENTE;
    }
}