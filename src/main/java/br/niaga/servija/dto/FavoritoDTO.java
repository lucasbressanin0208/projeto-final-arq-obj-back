package br.niaga.servija.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoritoDTO {

    private UUID clienteId;
    private UUID prestadorId;
}