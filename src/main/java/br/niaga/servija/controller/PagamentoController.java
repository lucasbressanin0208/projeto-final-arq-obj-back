package br.niaga.servija.controller;

import br.niaga.servija.dto.response.ResponsePagamentoDTO;
import br.niaga.servija.dto.save.SavePagamentoDTO;
import br.niaga.servija.models.StatusPagamento;
import br.niaga.servija.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<ResponsePagamentoDTO> criar(@RequestBody SavePagamentoDTO dto) {
        return ResponseEntity.status(201).body(pagamentoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponsePagamentoDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePagamentoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @GetMapping("/agendamento/{agendamentoId}")
    public ResponseEntity<ResponsePagamentoDTO> buscarPorAgendamento(@PathVariable UUID agendamentoId) {
        return ResponseEntity.ok(pagamentoService.buscarPorAgendamento(agendamentoId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ResponsePagamentoDTO>> listarPorStatus(@PathVariable StatusPagamento status) {
        return ResponseEntity.ok(pagamentoService.listarPorStatus(status));
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<ResponsePagamentoDTO> marcarComoPago(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.marcarComoPago(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ResponsePagamentoDTO> cancelar(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.cancelar(id));
    }
}
