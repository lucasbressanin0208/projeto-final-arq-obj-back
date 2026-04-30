package br.niaga.servija.dto.endereco;

import br.niaga.servija.models.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseEnderecoDTO {

    private UUID id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    public static ResponseEnderecoDTO toDTO(Endereco endereco) {
        ResponseEnderecoDTO dto = new ResponseEnderecoDTO();
        dto.setId(endereco.getId());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());
        dto.setComplemento(endereco.getComplemento());
        return dto;
    }
}