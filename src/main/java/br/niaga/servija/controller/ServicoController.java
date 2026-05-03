package br.niaga.servija.controller;

import br.niaga.servija.dto.edit.EditServicoDTO;
import br.niaga.servija.dto.response.ResponseServicoDTO;
import br.niaga.servija.dto.save.SaveServicoDTO;
import br.niaga.servija.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ResponseServicoDTO> criar(@RequestBody SaveServicoDTO dto) {
        return ResponseEntity.status(201).body(servicoService.criar(dto));
    }

    @GetMapping("/prestador/{prestadorId}")
    public ResponseEntity<List<ResponseServicoDTO>> listarPorPrestador(@PathVariable UUID prestadorId) {
        return ResponseEntity.ok(servicoService.listarPorPrestador(prestadorId));
    }

    @GetMapping("/prestador/{prestadorId}/ativos")
    public ResponseEntity<List<ResponseServicoDTO>> listarAtivosPorPrestador(@PathVariable UUID prestadorId) {
        return ResponseEntity.ok(servicoService.listarAtivosPorPrestador(prestadorId));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ResponseServicoDTO>> listarPorCategoria(@PathVariable UUID categoriaId) {
        return ResponseEntity.ok(servicoService.listarPorCategoria(categoriaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseServicoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseServicoDTO> atualizar(@PathVariable UUID id,
                                                        @RequestBody EditServicoDTO dto) {
        return ResponseEntity.ok(servicoService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable UUID id) {
        servicoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        servicoService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
