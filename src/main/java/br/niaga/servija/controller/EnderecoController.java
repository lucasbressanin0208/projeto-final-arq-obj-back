package br.niaga.servija.controller;

import br.niaga.servija.dto.edit.EditEnderecoDTO;
import br.niaga.servija.dto.response.ResponseEnderecoDTO;
import br.niaga.servija.dto.save.SaveEnderecoDTO;
import br.niaga.servija.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @PostMapping
    public ResponseEntity<ResponseEnderecoDTO> salvar(@Valid @RequestBody SaveEnderecoDTO dto) {
        return ResponseEntity.status(201).body(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseEnderecoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEnderecoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEnderecoDTO> editar(@PathVariable UUID id,
                                                      @Valid @RequestBody EditEnderecoDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
