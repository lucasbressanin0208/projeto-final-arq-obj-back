package br.niaga.servija.controller;

import br.niaga.servija.dto.EditCategoriaServicoDTO;
import br.niaga.servija.dto.ResponseCategoriaServicoDTO;
import br.niaga.servija.dto.SaveCategoriaServicoDTO;
import br.niaga.servija.service.CategoriaServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaServicoController {

    private final CategoriaServicoService categoriaServicoService;

    @PostMapping
    public ResponseEntity<ResponseCategoriaServicoDTO> criar(@RequestBody SaveCategoriaServicoDTO dto) {
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
                                                                 @RequestBody EditCategoriaServicoDTO dto) {
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
