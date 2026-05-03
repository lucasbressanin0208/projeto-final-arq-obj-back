package br.niaga.servija.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column
    private String cep;

    @Column
    private String complemento;

    public boolean pertenceACidade(String cidade) {
        return this.cidade.equalsIgnoreCase(cidade);
    }

    public String resumoLocalizacao() {
        return cidade + " - " + estado;
    }
}