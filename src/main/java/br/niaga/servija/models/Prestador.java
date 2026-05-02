package br.niaga.servija.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "prestadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String telefone;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal notaMedia;

    @Column(nullable = false)
    private Boolean ativo;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void atualizarNotaMedia(BigDecimal novaNotaMedia) {
        this.notaMedia = novaNotaMedia;
    }
}