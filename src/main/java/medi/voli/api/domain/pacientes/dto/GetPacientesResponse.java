package medi.voli.api.domain.pacientes.dto;

import medi.voli.api.domain.pacientes.repository.PacienteEntity;

public record GetPacientesResponse(
        Long id,
        String nome,
        String email,
        String cpf,
        Boolean ativo
) {
    public GetPacientesResponse(PacienteEntity paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getAtivo());
    }
}
