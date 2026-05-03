package br.niaga.servija.controller;

import br.niaga.servija.dto.EditDisponibilidadeDTO;
import br.niaga.servija.dto.ResponseDisponibilidadeDTO;
import br.niaga.servija.dto.SaveDisponibilidadeDTO;
import br.niaga.servija.service.DisponibilidadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prestadores/{prestadorId}/disponibilidades")
public class DisponibilidadeController {

    private final DisponibilidadeService service;

    public DisponibilidadeController(DisponibilidadeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseDisponibilidadeDTO salvar(
            @PathVariable UUID prestadorId,
            @RequestBody SaveDisponibilidadeDTO dto) {

        return service.salvar(prestadorId, dto);
    }

    @GetMapping
    public List<ResponseDisponibilidadeDTO> listar(
            @PathVariable UUID prestadorId) {

        return service.listarPorPrestador(prestadorId);
    }

    @PutMapping("/{id}")
    public ResponseDisponibilidadeDTO editar(
            @PathVariable UUID id,
            @RequestBody EditDisponibilidadeDTO dto) {

        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}