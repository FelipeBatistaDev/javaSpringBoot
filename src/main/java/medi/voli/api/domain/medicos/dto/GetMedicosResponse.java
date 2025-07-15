package medi.voli.api.domain.medicos.dto;

import medi.voli.api.domain.medicos.dto.enums.Especialidade;
import medi.voli.api.domain.medicos.repository.MedicoEntity;

public record GetMedicosResponse(
        Long id,
        String nome,
        String email,
        String crm,
        Boolean status,
        Especialidade especialidade
) {
    public GetMedicosResponse(MedicoEntity medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.isAtivo(), medico.getEspecialidade());
    }
}
