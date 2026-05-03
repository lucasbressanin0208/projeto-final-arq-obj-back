package br.niaga.servija.dto.save;

import br.niaga.servija.models.Prestador;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SavePrestadorDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String telefone;

    private String descricao;
    private UUID enderecoId;

    public Prestador toModel() {
        Prestador p = new Prestador();
        p.setNome(this.nome);
        p.setEmail(this.email);
        p.setSenha(this.senha);
        p.setTelefone(this.telefone);
        p.setDescricao(this.descricao);
        return p;
    }
}
