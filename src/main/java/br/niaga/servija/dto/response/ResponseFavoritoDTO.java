package br.niaga.servija.dto.response;

import br.niaga.servija.models.Favorito;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseFavoritoDTO {

    private UUID id;
    private UUID clienteId;
    private UUID prestadorId;
    private LocalDateTime dataCriacao;

    public static ResponseFavoritoDTO toDTO(Favorito f) {
        ResponseFavoritoDTO dto = new ResponseFavoritoDTO();
        dto.setId(f.getId());
        dto.setClienteId(f.getCliente() != null ? f.getCliente().getId() : null);
        dto.setPrestadorId(f.getPrestador() != null ? f.getPrestador().getId() : null);
        dto.setDataCriacao(f.getDataCriacao());
        return dto;
    }
}
