package br.niaga.servija.controller;

import br.niaga.servija.dto.edit.EditClienteDTO;
import br.niaga.servija.dto.response.ResponseClienteDTO;
import br.niaga.servija.dto.save.SaveClienteDTO;
import br.niaga.servija.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ResponseClienteDTO> criar(@Valid @RequestBody SaveClienteDTO dto) {
        return ResponseEntity.status(201).body(clienteService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseClienteDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClienteDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseClienteDTO> atualizar(@PathVariable UUID id,
                                                        @Valid @RequestBody EditClienteDTO dto) {
        return ResponseEntity.ok(clienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
