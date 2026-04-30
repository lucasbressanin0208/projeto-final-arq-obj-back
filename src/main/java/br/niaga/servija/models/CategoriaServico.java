package br.niaga.servija.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categorias_servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaServico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private Boolean ativa;

    @OneToMany(mappedBy = "categoria")
    private List<Servico> servicos;
}