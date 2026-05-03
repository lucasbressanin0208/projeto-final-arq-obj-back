package br.niaga.servija.controller;

import br.niaga.servija.dto.edit.EditCategoriaServicoDTO;
import br.niaga.servija.dto.response.ResponseCategoriaServicoDTO;
import br.niaga.servija.dto.save.SaveCategoriaServicoDTO;
import br.niaga.servija.service.CategoriaServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaServicoController {

    private final CategoriaServicoService categoriaServicoService;

    @PostMapping
    public ResponseEntity<ResponseCategoriaServicoDTO> criar(@Valid @RequestBody SaveCategoriaServicoDTO dto) {
        return ResponseEntity.status(201).body(categoriaServicoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseCategoriaServicoDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaServicoService.listarTodas());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<ResponseCategoriaServicoDTO>> listarAtivas() {
        return ResponseEntity.ok(categoriaServicoService.listarAtivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCategoriaServicoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(categoriaServicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCategoriaServicoDTO> atualizar(@PathVariable UUID id,
                                                                 @Valid @RequestBody EditCategoriaServicoDTO dto) {
        return ResponseEntity.ok(categoriaServicoService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable UUID id) {
        categoriaServicoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        categoriaServicoService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
