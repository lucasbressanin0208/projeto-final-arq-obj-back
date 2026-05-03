package br.niaga.servija.dto.save;

import br.niaga.servija.models.Prestador;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class SavePrestadorDTO {

    private String nome;
    private String email;
    private String senha;
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
        p.setNotaMedia(BigDecimal.ZERO);
        p.setAtivo(true);
        return p;
    }
}
