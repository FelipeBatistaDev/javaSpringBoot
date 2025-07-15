package medi.voli.api.domain.consultas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import medi.voli.api.domain.medicos.dto.enums.Especialidade;

import java.time.LocalDateTime;

public record DadosCadastroConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}