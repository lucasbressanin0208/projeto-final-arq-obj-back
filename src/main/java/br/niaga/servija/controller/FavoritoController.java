package br.niaga.servija.controller;

import br.niaga.servija.dto.response.ResponseFavoritoDTO;
import br.niaga.servija.dto.save.SaveFavoritoDTO;
import br.niaga.servija.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    @PostMapping
    public ResponseEntity<ResponseFavoritoDTO> criar(@RequestBody SaveFavoritoDTO dto) {
        return ResponseEntity.status(201).body(favoritoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseFavoritoDTO>> listarTodos() {
        return ResponseEntity.ok(favoritoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFavoritoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(favoritoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        favoritoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
