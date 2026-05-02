package br.niaga.servija.service;

import br.niaga.servija.dto.FavoritoDTO;
import br.niaga.servija.models.*;
import br.niaga.servija.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final ClienteRepository clienteRepository;
    private final PrestadorRepository prestadorRepository;

    public Favorito criar(FavoritoDTO dto) {

        if (favoritoRepository.existsByClienteIdAndPrestadorId(
                dto.getClienteId(), dto.getPrestadorId())) {
            throw new IllegalArgumentException("Prestador já favoritado por este cliente");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Prestador prestador = prestadorRepository.findById(dto.getPrestadorId())
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));

        Favorito favorito = Favorito.builder()
                .cliente(cliente)
                .prestador(prestador)
                .dataCriacao(LocalDateTime.now())
                .build();

        return favoritoRepository.save(favorito);
    }

    public List<Favorito> listarTodos() {
        return favoritoRepository.findAll();
    }

    public Favorito buscarPorId(UUID id) {
        return favoritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado"));
    }

    public void deletar(UUID id) {
        Favorito favorito = buscarPorId(id);
        favoritoRepository.delete(favorito);
    }
}