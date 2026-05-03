package br.niaga.servija.dto.save;

import br.niaga.servija.models.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private TipoUsuario tipo;
}
