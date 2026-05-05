package br.niaga.servija.service;

import br.niaga.servija.dto.edit.EditPrestadorDTO;
import br.niaga.servija.dto.response.ResponsePrestadorDTO;
import br.niaga.servija.dto.save.SavePrestadorDTO;
import br.niaga.servija.models.Endereco;
import br.niaga.servija.models.Prestador;
import br.niaga.servija.repository.EnderecoRepository;
import br.niaga.servija.repository.PrestadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrestadorService {

    private final PrestadorRepository prestadorRepository;
    private final EnderecoRepository enderecoRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponsePrestadorDTO criar(SavePrestadorDTO dto) {
        if (prestadorRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Prestador prestador = dto.toModel();
        prestador.setSenha(passwordEncoder.encode(dto.getSenha()));
        prestador.setNotaMedia(BigDecimal.ZERO);
        prestador.setAtivo(true);
        if (dto.getEnderecoId() != null) {
            Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
            prestador.setEndereco(endereco);
        } else if (dto.getCidade() != null && !dto.getCidade().isBlank()) {
            Endereco endereco = new Endereco();
            endereco.setRua(dto.getRua() != null && !dto.getRua().isBlank() ? dto.getRua() : "Não informado");
            endereco.setNumero(dto.getNumero() != null && !dto.getNumero().isBlank() ? dto.getNumero() : "S/N");
            endereco.setBairro(dto.getBairro() != null && !dto.getBairro().isBlank() ? dto.getBairro() : "Não informado");
            endereco.setCidade(dto.getCidade());
            endereco.setEstado(dto.getEstado() != null && !dto.getEstado().isBlank() ? dto.getEstado() : "Não informado");
            endereco.setCep(dto.getCep());
            endereco.setComplemento(dto.getComplemento());
            prestador.setEndereco(enderecoRepository.save(endereco));
        }
        return ResponsePrestadorDTO.toDTO(prestadorRepository.save(prestador));
    }

    public List<ResponsePrestadorDTO> listarTodos() {
        return prestadorRepository.findAll().stream()
                .map(ResponsePrestadorDTO::toDTO)
                .toList();
    }

    public List<ResponsePrestadorDTO> listarAtivos() {
        return prestadorRepository.findAllByAtivoTrue().stream()
                .map(ResponsePrestadorDTO::toDTO)
                .toList();
    }

    public List<ResponsePrestadorDTO> buscarPorCidade(String cidade) {
        return prestadorRepository.findAllByEnderecoCidadeIgnoreCaseAndAtivoTrue(cidade).stream()
                .map(ResponsePrestadorDTO::toDTO)
                .toList();
    }

    public List<ResponsePrestadorDTO> buscarPorCidadeEBairro(String cidade, String bairro) {
        return prestadorRepository.findAllByEnderecoCidadeIgnoreCaseAndEnderecoBairroIgnoreCaseAndAtivoTrue(cidade, bairro).stream()
                .map(ResponsePrestadorDTO::toDTO)
                .toList();
    }

    public ResponsePrestadorDTO buscarPorId(UUID id) {
        return ResponsePrestadorDTO.toDTO(buscarOuFalhar(id));
    }

    public ResponsePrestadorDTO atualizar(UUID id, EditPrestadorDTO dto) {
        Prestador prestador = buscarOuFalhar(id);
        prestador.setNome(dto.getNome());
        prestador.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            prestador.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        prestador.setTelefone(dto.getTelefone());
        prestador.setDescricao(dto.getDescricao());
        if (dto.getEnderecoId() != null) {
            Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
            prestador.setEndereco(endereco);
        }
        return ResponsePrestadorDTO.toDTO(prestadorRepository.save(prestador));
    }

    public void ativar(UUID id) {
        Prestador prestador = buscarOuFalhar(id);
        prestador.ativar();
        prestadorRepository.save(prestador);
    }

    public void desativar(UUID id) {
        Prestador prestador = buscarOuFalhar(id);
        prestador.desativar();
        prestadorRepository.save(prestador);
    }

    private Prestador buscarOuFalhar(UUID id) {
        return prestadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));
    }
}
