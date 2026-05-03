package br.niaga.servija.controller;

import br.niaga.servija.dto.response.ResponseAgendamentoDTO;
import br.niaga.servija.dto.save.SaveAgendamentoDTO;
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
    public ResponseEntity<ResponseAgendamentoDTO> criar(@RequestBody SaveAgendamentoDTO dto) {
        return ResponseEntity.status(201).body(agendamentoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseAgendamentoDTO>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAgendamentoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ResponseAgendamentoDTO>> listarPorCliente(@PathVariable UUID clienteId) {
        return ResponseEntity.ok(agendamentoService.listarPorCliente(clienteId));
    }

    @GetMapping("/prestador/{prestadorId}")
    public ResponseEntity<List<ResponseAgendamentoDTO>> listarPorPrestador(@PathVariable UUID prestadorId) {
        return ResponseEntity.ok(agendamentoService.listarPorPrestador(prestadorId));
    }

    @GetMapping("/prestador/{prestadorId}/status/{status}")
    public ResponseEntity<List<ResponseAgendamentoDTO>> listarPorPrestadorEStatus(
            @PathVariable UUID prestadorId,
            @PathVariable StatusAgendamento status) {
        return ResponseEntity.ok(agendamentoService.listarPorPrestadorEStatus(prestadorId, status));
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<ResponseAgendamentoDTO> confirmar(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.confirmar(id));
    }

    @PatchMapping("/{id}/recusar")
    public ResponseEntity<ResponseAgendamentoDTO> recusar(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.recusar(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ResponseAgendamentoDTO> cancelar(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.cancelar(id));
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<ResponseAgendamentoDTO> concluir(@PathVariable UUID id) {
        return ResponseEntity.ok(agendamentoService.concluir(id));
    }
}
