package br.niaga.servija.dto.response;

import br.niaga.servija.models.Favorito;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseFavoritoDTO {

    private UUID id;
    private UUID clienteId;
    private UUID prestadorId;
    private String prestadorNome;
    private String prestadorCidade;
    private BigDecimal prestadorNotaMedia;
    private LocalDateTime dataCriacao;

    public static ResponseFavoritoDTO toDTO(Favorito f) {
        ResponseFavoritoDTO dto = new ResponseFavoritoDTO();
        dto.setId(f.getId());
        dto.setClienteId(f.getCliente() != null ? f.getCliente().getId() : null);
        if (f.getPrestador() != null) {
            dto.setPrestadorId(f.getPrestador().getId());
            dto.setPrestadorNome(f.getPrestador().getNome());
            dto.setPrestadorNotaMedia(f.getPrestador().getNotaMedia());
            if (f.getPrestador().getEndereco() != null) {
                dto.setPrestadorCidade(f.getPrestador().getEndereco().getCidade());
            }
        }
        dto.setDataCriacao(f.getDataCriacao());
        return dto;
    }
}
