package br.niaga.servija.service;

import br.niaga.servija.dto.PrestadorDTO;
import br.niaga.servija.models.Endereco;
import br.niaga.servija.models.Prestador;
import br.niaga.servija.repository.EnderecoRepository;
import br.niaga.servija.repository.PrestadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrestadorService {

    private final PrestadorRepository prestadorRepository;
    private final EnderecoRepository enderecoRepository;

    public Prestador criar(PrestadorDTO dto) {
        if (prestadorRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Endereco endereco = null;

        if (dto.getEnderecoId() != null) {
            endereco = enderecoRepository.findById(dto.getEnderecoId())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        }

        Prestador prestador = Prestador.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .telefone(dto.getTelefone())
                .descricao(dto.getDescricao())
                .notaMedia(BigDecimal.ZERO)
                .ativo(true)
                .endereco(endereco)
                .build();

        return prestadorRepository.save(prestador);
    }

    public List<Prestador> listarTodos() {
        return prestadorRepository.findAll();
    }

    public List<Prestador> listarAtivos() {
        return prestadorRepository.findAllByAtivoTrue();
    }

    public List<Prestador> buscarPorCidade(String cidade) {
        return prestadorRepository.findAllByEnderecoCidadeIgnoreCaseAndAtivoTrue(cidade);
    }

    public List<Prestador> buscarPorCidadeEBairro(String cidade, String bairro) {
        return prestadorRepository.findAllByEnderecoCidadeIgnoreCaseAndEnderecoBairroIgnoreCaseAndAtivoTrue(cidade, bairro);
    }

    public Prestador buscarPorId(UUID id) {
        return prestadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));
    }

    public Prestador atualizar(UUID id, PrestadorDTO dto) {
        Prestador prestador = buscarPorId(id);

        prestador.setNome(dto.getNome());
        prestador.setEmail(dto.getEmail());
        prestador.setSenha(dto.getSenha());
        prestador.setTelefone(dto.getTelefone());
        prestador.setDescricao(dto.getDescricao());

        if (dto.getEnderecoId() != null) {
            Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

            prestador.setEndereco(endereco);
        }

        return prestadorRepository.save(prestador);
    }

    public void ativar(UUID id) {
        Prestador prestador = buscarPorId(id);
        prestador.ativar();
        prestadorRepository.save(prestador);
    }

    public void desativar(UUID id) {
        Prestador prestador = buscarPorId(id);
        prestador.desativar();
        prestadorRepository.save(prestador);
    }
}