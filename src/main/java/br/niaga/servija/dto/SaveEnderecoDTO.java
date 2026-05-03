package br.niaga.servija.dto;

import br.niaga.servija.models.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveEnderecoDTO {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    public Endereco toModel() {
        Endereco endereco = new Endereco();
        endereco.setRua(this.rua);
        endereco.setNumero(this.numero);
        endereco.setBairro(this.bairro);
        endereco.setCidade(this.cidade);
        endereco.setEstado(this.estado);
        endereco.setCep(this.cep);
        endereco.setComplemento(this.complemento);
        return endereco;
    }
}