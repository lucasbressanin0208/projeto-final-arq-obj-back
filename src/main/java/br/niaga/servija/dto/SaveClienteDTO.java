package br.niaga.servija.dto;

import br.niaga.servija.models.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveClienteDTO {

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;

    public Cliente toModel() {
        Cliente c = new Cliente();
        c.setNome(this.nome);
        c.setEmail(this.email);
        c.setSenha(this.senha);
        c.setTelefone(this.telefone);
        c.setCpf(this.cpf);
        return c;
    }
}
