package br.niaga.servija.service;

import br.niaga.servija.dto.response.TokenResponseDTO;
import br.niaga.servija.dto.save.LoginDTO;
import br.niaga.servija.models.Cliente;
import br.niaga.servija.models.Prestador;
import br.niaga.servija.repository.ClienteRepository;
import br.niaga.servija.repository.PrestadorRepository;
import br.niaga.servija.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final PrestadorRepository prestadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public TokenResponseDTO login(LoginDTO dto) {
        return switch (dto.getTipo()) {
            case CLIENTE -> loginCliente(dto);
            case PRESTADOR -> loginPrestador(dto);
        };
    }

    private TokenResponseDTO loginCliente(LoginDTO dto) {
        Cliente cliente = clienteRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        if (!passwordEncoder.matches(dto.getSenha(), cliente.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        String token = jwtUtil.gerarToken(
                cliente.getId().toString(), cliente.getEmail(), "ROLE_CLIENTE");
        return new TokenResponseDTO(token, "Bearer", "ROLE_CLIENTE");
    }

    private TokenResponseDTO loginPrestador(LoginDTO dto) {
        Prestador prestador = prestadorRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        if (!passwordEncoder.matches(dto.getSenha(), prestador.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        String token = jwtUtil.gerarToken(
                prestador.getId().toString(), prestador.getEmail(), "ROLE_PRESTADOR");
        return new TokenResponseDTO(token, "Bearer", "ROLE_PRESTADOR");
    }
}
