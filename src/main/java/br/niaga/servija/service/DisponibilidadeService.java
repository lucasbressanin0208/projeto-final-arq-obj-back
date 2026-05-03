package br.niaga.servija.service;

import br.niaga.servija.dto.edit.EditDisponibilidadeDTO;
import br.niaga.servija.dto.response.ResponseDisponibilidadeDTO;
import br.niaga.servija.dto.save.SaveDisponibilidadeDTO;
import br.niaga.servija.models.Disponibilidade;
import br.niaga.servija.models.Prestador;
import br.niaga.servija.repository.DisponibilidadeRepository;
import br.niaga.servija.repository.PrestadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository repository;
    private final PrestadorRepository prestadorRepository;

    public DisponibilidadeService(DisponibilidadeRepository repository,
                                  PrestadorRepository prestadorRepository) {
        this.repository = repository;
        this.prestadorRepository = prestadorRepository;
    }

    // post
    public ResponseDisponibilidadeDTO salvar(UUID prestadorId, SaveDisponibilidadeDTO dto) {

        Prestador prestador = prestadorRepository.findById(prestadorId)
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));

        Disponibilidade d = dto.toModel();
        d.setPrestador(prestador);

        validarDisponibilidade(d);

        return ResponseDisponibilidadeDTO.toDTO(repository.save(d));
    }

    // get por prestador
    public List<ResponseDisponibilidadeDTO> listarPorPrestador(UUID prestadorId) {
        Prestador prestador = buscarPrestador(prestadorId);

        return repository.findByPrestador(prestador)
                .stream()
                .map(ResponseDisponibilidadeDTO::toDTO)
                .collect(Collectors.toList());
    }

    // update
    public ResponseDisponibilidadeDTO editar(UUID id, EditDisponibilidadeDTO dto) {
        Disponibilidade d = buscarOuFalhar(id);

        d.setDiaSemana(dto.getDiaSemana());
        d.setHoraInicio(dto.getHoraInicio());
        d.setHoraFim(dto.getHoraFim());
        d.setAtiva(dto.getAtiva());

        validarDisponibilidade(d);

        return ResponseDisponibilidadeDTO.toDTO(repository.save(d));
    }

    // delete
    public void deletar(UUID id) {
        Disponibilidade d = buscarOuFalhar(id);
        repository.delete(d);
    }

    // validacoes

    private void validarDisponibilidade(Disponibilidade d) {

        // horario válido
        if (!d.horarioValido()) {
            throw new RuntimeException("Hora fim deve ser depois da hora início");
        }

        // conflito no banco
        List<Disponibilidade> conflitos =
                repository.findByPrestadorAndDiaSemanaAndHoraInicioLessThanAndHoraFimGreaterThan(
                        d.getPrestador(),
                        d.getDiaSemana(),
                        d.getHoraFim(),
                        d.getHoraInicio()
                );

        // evitar conflito consigo mesmo (update)
        conflitos = conflitos.stream()
                .filter(c -> !c.getId().equals(d.getId()))
                .toList();

        if (!conflitos.isEmpty()) {
            throw new RuntimeException("Já existe disponibilidade nesse horário");
        }
    }


    private Disponibilidade buscarOuFalhar(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada"));
    }

    private Prestador buscarPrestador(UUID id) {
        return prestadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));
    }
}