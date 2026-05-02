package br.niaga.servija.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestadorDTO {

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String descricao;
    private UUID enderecoId;
}