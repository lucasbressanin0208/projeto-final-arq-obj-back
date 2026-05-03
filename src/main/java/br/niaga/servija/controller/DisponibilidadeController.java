package br.niaga.servija.controller;

import br.niaga.servija.dto.edit.EditDisponibilidadeDTO;
import br.niaga.servija.dto.response.ResponseDisponibilidadeDTO;
import br.niaga.servija.dto.save.SaveDisponibilidadeDTO;
import br.niaga.servija.service.DisponibilidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prestadores/{prestadorId}/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadeController {

    private final DisponibilidadeService service;

    @PostMapping
    public ResponseEntity<ResponseDisponibilidadeDTO> salvar(
            @PathVariable UUID prestadorId,
            @Valid @RequestBody SaveDisponibilidadeDTO dto) {
        return ResponseEntity.status(201).body(service.salvar(prestadorId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDisponibilidadeDTO>> listar(@PathVariable UUID prestadorId) {
        return ResponseEntity.ok(service.listarPorPrestador(prestadorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDisponibilidadeDTO> editar(
            @PathVariable UUID id,
            @Valid @RequestBody EditDisponibilidadeDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
