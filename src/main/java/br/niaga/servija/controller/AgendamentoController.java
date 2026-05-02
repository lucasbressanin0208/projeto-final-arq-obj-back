package br.niaga.servija.controller;

import br.niaga.servija.dto.AgendamentoDTO;
import br.niaga.servija.models.Agendamento;
import br.niaga.servija.models.StatusAgendamento;
import br.niaga.servija.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody AgendamentoDTO dto) {
        Agendamento criado = agendamentoService.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Agendamento>> listarPorCliente(@PathVariable UUID clienteId) {
        return ResponseEntity.ok(agendamentoService.listarPorCliente(clienteId));
    }

    @GetMapping("/prestador/{prestadorId}")
    public ResponseEntity<List<Agendamento>> listarPorPrestador(@PathVariable UUID prestadorId) {
        return ResponseEntity.ok(agendamentoService.listarPorPrestador(prestadorId));
    }

    @GetMapping("/prestador/{prestadorId}/status/{status}")
    public ResponseEntity<List<Agendamento>> listarPorPrestadorEStatus(
            @PathVariable UUID prestadorId,
            @PathVariable StatusAgendamento status
    ) {
        return ResponseEntity.ok(agendamentoService.listarPorPrestadorEStatus(prestadorId, status));
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<Agendamento> confirmar(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.confirmar(id));
    }

    @PatchMapping("/{id}/recusar")
    public ResponseEntity<Agendamento> recusar(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.recusar(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Agendamento> cancelar(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.cancelar(id));
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Agendamento> concluir(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.concluir(id));
    }
}