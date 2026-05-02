package br.niaga.servija.controller;

import br.niaga.servija.dto.FavoritoDTO;
import br.niaga.servija.models.Favorito;
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
    public ResponseEntity<Favorito> criar(@RequestBody FavoritoDTO dto) {
        Favorito criado = favoritoService.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Favorito>> listarTodos() {
        return ResponseEntity.ok(favoritoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorito> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(favoritoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        favoritoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}