package medi.voli.api.domain.medicos.dto;

import jakarta.validation.constraints.NotNull;
import medi.voli.api.domain.enderecos.dto.DadosEndereco;

public record DadosEditaveis(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEndereco endereco
) {
}
