package br.niaga.servija.controller;

import br.niaga.servija.dto.CategoriaServicoDTO;
import br.niaga.servija.models.CategoriaServico;
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
    public ResponseEntity<CategoriaServico> criar(@RequestBody CategoriaServicoDTO dto) {
        CategoriaServico criada = categoriaServicoService.criar(dto);
        return ResponseEntity.status(201).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaServico>> listarTodas() {
        return ResponseEntity.ok(categoriaServicoService.listarTodas());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<CategoriaServico>> listarAtivas() {
        return ResponseEntity.ok(categoriaServicoService.listarAtivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaServico> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(categoriaServicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaServico> atualizar(@PathVariable UUID id,
                                                      @RequestBody CategoriaServicoDTO dto) {
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
