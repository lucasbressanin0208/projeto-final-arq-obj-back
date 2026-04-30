package br.niaga.servija.controller;

import br.niaga.servija.dto.ServicoDTO;
import br.niaga.servija.models.Servico;
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
    public ResponseEntity<Servico> criar(@RequestBody ServicoDTO dto) {
        Servico criado = servicoService.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping("/prestador/{prestadorId}")
    public ResponseEntity<List<Servico>> listarPorPrestador(@PathVariable UUID prestadorId) {
        return ResponseEntity.ok(servicoService.listarPorPrestador(prestadorId));
    }

    @GetMapping("/prestador/{prestadorId}/ativos")
    public ResponseEntity<List<Servico>> listarAtivosPorPrestador(@PathVariable UUID prestadorId) {
        return ResponseEntity.ok(servicoService.listarAtivosPorPrestador(prestadorId));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Servico>> listarPorCategoria(@PathVariable UUID categoriaId) {
        return ResponseEntity.ok(servicoService.listarPorCategoria(categoriaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable UUID id,
                                             @RequestBody ServicoDTO dto) {
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
