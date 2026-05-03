package br.niaga.servija.controller;

import br.niaga.servija.dto.response.ResponseAvaliacaoDTO;
import br.niaga.servija.dto.save.SaveAvaliacaoDTO;
import br.niaga.servija.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<ResponseAvaliacaoDTO> criar(@Valid @RequestBody SaveAvaliacaoDTO dto) {
        return ResponseEntity.status(201).body(avaliacaoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseAvaliacaoDTO>> listarTodos() {
        return ResponseEntity.ok(avaliacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAvaliacaoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(avaliacaoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
