package br.niaga.servija.service;

import br.niaga.servija.dto.EditEnderecoDTO;
import br.niaga.servija.dto.ResponseEnderecoDTO;
import br.niaga.servija.dto.SaveEnderecoDTO;
import br.niaga.servija.models.Endereco;
import br.niaga.servija.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    // post
    public ResponseEnderecoDTO salvar(SaveEnderecoDTO dto) {
        Endereco endereco = dto.toModel();

        validarEndereco(endereco);

        return ResponseEnderecoDTO.toDTO(repository.save(endereco));
    }

    // get all
    public List<ResponseEnderecoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(ResponseEnderecoDTO::toDTO)
                .collect(Collectors.toList());
    }

    // get por id
    public ResponseEnderecoDTO buscarPorId(UUID id) {
        Endereco endereco = buscarOuFalhar(id);
        return ResponseEnderecoDTO.toDTO(endereco);
    }

    // update
    public ResponseEnderecoDTO editar(UUID id, EditEnderecoDTO dto) {
        Endereco endereco = buscarOuFalhar(id);

        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
        endereco.setComplemento(dto.getComplemento());

        validarEndereco(endereco);

        return ResponseEnderecoDTO.toDTO(repository.save(endereco));
    }

    // delete
    public void deletar(UUID id) {
        Endereco endereco = buscarOuFalhar(id);
        repository.delete(endereco);
    }

    // validacao
    private void validarEndereco(Endereco e) {
        if (e.getCidade() == null || e.getEstado() == null) {
            throw new RuntimeException("Cidade e estado são obrigatórios");
        }
    }

    private Endereco buscarOuFalhar(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }
}