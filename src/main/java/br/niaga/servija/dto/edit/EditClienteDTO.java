package br.niaga.servija.dto.edit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditClienteDTO {

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
}
