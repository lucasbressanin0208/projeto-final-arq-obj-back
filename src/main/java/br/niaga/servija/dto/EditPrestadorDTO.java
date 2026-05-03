package br.niaga.servija.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EditPrestadorDTO {

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String descricao;
    private UUID enderecoId;
}
