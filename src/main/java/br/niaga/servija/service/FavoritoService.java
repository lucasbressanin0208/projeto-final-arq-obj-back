package br.niaga.servija.service;

import br.niaga.servija.dto.response.ResponseFavoritoDTO;
import br.niaga.servija.dto.save.SaveFavoritoDTO;
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

    public ResponseFavoritoDTO criar(SaveFavoritoDTO dto) {
        if (favoritoRepository.existsByClienteIdAndPrestadorId(dto.getClienteId(), dto.getPrestadorId())) {
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

        return ResponseFavoritoDTO.toDTO(favoritoRepository.save(favorito));
    }

    public List<ResponseFavoritoDTO> listarTodos() {
        return favoritoRepository.findAll().stream()
                .map(ResponseFavoritoDTO::toDTO)
                .toList();
    }

    public ResponseFavoritoDTO buscarPorId(UUID id) {
        return ResponseFavoritoDTO.toDTO(buscarOuFalhar(id));
    }

    public void deletar(UUID id) {
        favoritoRepository.delete(buscarOuFalhar(id));
    }

    private Favorito buscarOuFalhar(UUID id) {
        return favoritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado"));
    }
}
