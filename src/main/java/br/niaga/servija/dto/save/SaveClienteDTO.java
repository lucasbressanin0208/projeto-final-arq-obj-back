package br.niaga.servija.dto.save;

import br.niaga.servija.models.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveClienteDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
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
