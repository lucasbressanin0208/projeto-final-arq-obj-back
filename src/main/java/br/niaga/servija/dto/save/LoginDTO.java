package br.niaga.servija.dto.save;

import br.niaga.servija.models.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String email;
    private String senha;
    private TipoUsuario tipo;
}
