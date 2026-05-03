package br.niaga.servija.controller;

import br.niaga.servija.dto.EditEnderecoDTO;
import br.niaga.servija.dto.ResponseEnderecoDTO;
import br.niaga.servija.dto.SaveEnderecoDTO;
import br.niaga.servija.service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEnderecoDTO salvar(@RequestBody SaveEnderecoDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<ResponseEnderecoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEnderecoDTO buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEnderecoDTO editar(@PathVariable UUID id,
                                      @RequestBody EditEnderecoDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}