package br.niaga.servija.controller;

import br.niaga.servija.dto.PrestadorDTO;
import br.niaga.servija.models.Prestador;
import br.niaga.servija.service.PrestadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prestadores")
@RequiredArgsConstructor
public class PrestadorController {

    private final PrestadorService prestadorService;

    @PostMapping
    public ResponseEntity<Prestador> criar(@RequestBody PrestadorDTO dto) {
        Prestador criado = prestadorService.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Prestador>> listarTodos() {
        return ResponseEntity.ok(prestadorService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Prestador>> listarAtivos() {
        return ResponseEntity.ok(prestadorService.listarAtivos());
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Prestador>> buscarPorCidade(@PathVariable String cidade) {
        return ResponseEntity.ok(prestadorService.buscarPorCidade(cidade));
    }

    @GetMapping("/cidade/{cidade}/bairro/{bairro}")
    public ResponseEntity<List<Prestador>> buscarPorCidadeEBairro(@PathVariable String cidade,
                                                                  @PathVariable String bairro) {
        return ResponseEntity.ok(prestadorService.buscarPorCidadeEBairro(cidade, bairro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestador> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(prestadorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestador> atualizar(@PathVariable UUID id,
                                               @RequestBody PrestadorDTO dto) {
        return ResponseEntity.ok(prestadorService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable UUID id) {
        prestadorService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        prestadorService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}