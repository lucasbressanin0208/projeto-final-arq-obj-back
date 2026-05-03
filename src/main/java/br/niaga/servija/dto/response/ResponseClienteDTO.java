package br.niaga.servija.dto.response;

import br.niaga.servija.models.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseClienteDTO {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    public static ResponseClienteDTO toDTO(Cliente c) {
        ResponseClienteDTO dto = new ResponseClienteDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setEmail(c.getEmail());
        dto.setTelefone(c.getTelefone());
        dto.setCpf(c.getCpf());
        return dto;
    }
}
