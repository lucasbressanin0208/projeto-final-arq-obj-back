package br.niaga.servija.service;

import br.niaga.servija.dto.edit.EditClienteDTO;
import br.niaga.servija.dto.response.ResponseClienteDTO;
import br.niaga.servija.dto.save.SaveClienteDTO;
import br.niaga.servija.models.Cliente;
import br.niaga.servija.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseClienteDTO criar(SaveClienteDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (dto.getCpf() != null && clienteRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        Cliente cliente = dto.toModel();
        cliente.setSenha(passwordEncoder.encode(dto.getSenha()));
        return ResponseClienteDTO.toDTO(clienteRepository.save(cliente));
    }

    public List<ResponseClienteDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ResponseClienteDTO::toDTO)
                .toList();
    }

    public ResponseClienteDTO buscarPorId(UUID id) {
        return ResponseClienteDTO.toDTO(buscarOuFalhar(id));
    }

    public ResponseClienteDTO atualizar(UUID id, EditClienteDTO dto) {
        Cliente cliente = buscarOuFalhar(id);
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            cliente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        cliente.setTelefone(dto.getTelefone());
        cliente.setCpf(dto.getCpf());
        return ResponseClienteDTO.toDTO(clienteRepository.save(cliente));
    }

    public void deletar(UUID id) {
        clienteRepository.delete(buscarOuFalhar(id));
    }

    private Cliente buscarOuFalhar(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}
