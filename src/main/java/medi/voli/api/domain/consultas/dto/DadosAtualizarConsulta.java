package medi.voli.api.domain.consultas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import medi.voli.api.domain.medicos.dto.enums.Especialidade;

import java.time.LocalDateTime;

public record DadosAtualizarConsulta(
        @NotNull
        Long idConsulta,

        Long idMedico,

        @Future
        LocalDateTime data
) {
}
