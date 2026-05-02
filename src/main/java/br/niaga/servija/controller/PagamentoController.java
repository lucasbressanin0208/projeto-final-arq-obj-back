package br.niaga.servija.controller;

import br.niaga.servija.dto.PagamentoDTO;
import br.niaga.servija.models.Pagamento;
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
    public ResponseEntity<Pagamento> criar(@RequestBody PagamentoDTO dto) {
        Pagamento criado = pagamentoService.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @GetMapping("/agendamento/{agendamentoId}")
    public ResponseEntity<Pagamento> buscarPorAgendamento(@PathVariable UUID agendamentoId) {
        return ResponseEntity.ok(pagamentoService.buscarPorAgendamento(agendamentoId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pagamento>> listarPorStatus(@PathVariable StatusPagamento status) {
        return ResponseEntity.ok(pagamentoService.listarPorStatus(status));
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<Pagamento> marcarComoPago(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.marcarComoPago(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Pagamento> cancelar(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.cancelar(id));
    }
}