package br.niaga.servija.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "servicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer duracaoMinutos;

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaServico categoria;

    @ManyToOne
    @JoinColumn(name = "prestador_id", nullable = false)
    private Prestador prestador;

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public LocalDateTime calcularHorarioFim(LocalDateTime dataHoraInicio) {
        return dataHoraInicio.plusMinutes(duracaoMinutos);
    }
}