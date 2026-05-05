package br.niaga.servija.dto.response;

import br.niaga.servija.models.Prestador;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ResponsePrestadorDTO {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String descricao;
    private BigDecimal notaMedia;
    private Boolean ativo;
    private ResponseEnderecoDTO endereco;
    private String cidade;
    private String bairro;

    public static ResponsePrestadorDTO toDTO(Prestador p) {
        ResponsePrestadorDTO dto = new ResponsePrestadorDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEmail(p.getEmail());
        dto.setTelefone(p.getTelefone());
        dto.setDescricao(p.getDescricao());
        dto.setNotaMedia(p.getNotaMedia());
        dto.setAtivo(p.getAtivo());
        if (p.getEndereco() != null) {
            dto.setEndereco(ResponseEnderecoDTO.toDTO(p.getEndereco()));
            dto.setCidade(p.getEndereco().getCidade());
            dto.setBairro(p.getEndereco().getBairro());
        }
        return dto;
    }
}
