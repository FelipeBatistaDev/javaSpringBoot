package medi.voli.api.domain.consultas.dto;

import medi.voli.api.domain.consultas.repository.ConsultaEntity;

import java.time.LocalDateTime;

public record DetalhesConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
) {
    public DetalhesConsulta(ConsultaEntity consultaAgendada) {
        this(consultaAgendada.getId(),consultaAgendada.getMedico().getId(),consultaAgendada.getPaciente().getId(), consultaAgendada.getData());
    }
}
