package br.niaga.servija.controller;

import br.niaga.servija.dto.edit.EditPrestadorDTO;
import br.niaga.servija.dto.response.ResponsePrestadorDTO;
import br.niaga.servija.dto.save.SavePrestadorDTO;
import br.niaga.servija.service.PrestadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prestadores")
@RequiredArgsConstructor
public class PrestadorController {

    private final PrestadorService prestadorService;

    @PostMapping
    public ResponseEntity<ResponsePrestadorDTO> criar(@Valid @RequestBody SavePrestadorDTO dto) {
        return ResponseEntity.status(201).body(prestadorService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponsePrestadorDTO>> listarTodos() {
        return ResponseEntity.ok(prestadorService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ResponsePrestadorDTO>> listarAtivos() {
        return ResponseEntity.ok(prestadorService.listarAtivos());
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<ResponsePrestadorDTO>> buscarPorCidade(@PathVariable String cidade) {
        return ResponseEntity.ok(prestadorService.buscarPorCidade(cidade));
    }

    @GetMapping("/cidade/{cidade}/bairro/{bairro}")
    public ResponseEntity<List<ResponsePrestadorDTO>> buscarPorCidadeEBairro(@PathVariable String cidade,
                                                                              @PathVariable String bairro) {
        return ResponseEntity.ok(prestadorService.buscarPorCidadeEBairro(cidade, bairro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePrestadorDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(prestadorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePrestadorDTO> atualizar(@PathVariable UUID id,
                                                          @Valid @RequestBody EditPrestadorDTO dto) {
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
