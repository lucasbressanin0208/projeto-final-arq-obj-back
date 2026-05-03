package br.niaga.servija.dto.save;

import br.niaga.servija.models.CategoriaServico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCategoriaServicoDTO {

    private String nome;
    private String descricao;
    private Boolean ativa;

    public CategoriaServico toModel() {
        CategoriaServico c = new CategoriaServico();
        c.setNome(this.nome);
        c.setDescricao(this.descricao);
        c.setAtiva(this.ativa);
        return c;
    }
}
