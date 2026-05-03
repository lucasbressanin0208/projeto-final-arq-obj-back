package br.niaga.servija.dto.save;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveFavoritoDTO {

    private UUID clienteId;
    private UUID prestadorId;
}
