package br.niaga.servija.dto.endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditEnderecoDTO {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;
}