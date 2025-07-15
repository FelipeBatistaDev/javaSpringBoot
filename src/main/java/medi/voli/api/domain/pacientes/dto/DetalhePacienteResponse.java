package medi.voli.api.domain.pacientes.dto;

import medi.voli.api.domain.enderecos.dto.DadosEndereco;
import medi.voli.api.domain.pacientes.repository.PacienteEntity;

public record DetalhePacienteResponse(
         Long id,
         String nome,
         String email,
         String telefone,
         String cpf,
         Boolean ativo,
         DadosEndereco endereco
) {

    public DetalhePacienteResponse(PacienteEntity paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getAtivo(), paciente.getEndereco());
    }
}
