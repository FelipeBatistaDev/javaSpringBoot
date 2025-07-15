package medi.voli.api.domain.pacientes.dto;

import jakarta.validation.constraints.NotNull;

public record DadosParaAtualizar(
        @NotNull
        Long id ,

        String nome,

        String email
) {
}
