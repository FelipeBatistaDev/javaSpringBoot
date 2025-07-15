package medi.voli.api.domain.medicos.dto;

import medi.voli.api.domain.medicos.dto.enums.Especialidade;
import medi.voli.api.domain.enderecos.repository.Endereco;
import medi.voli.api.domain.medicos.repository.MedicoEntity;

public record RetornoDetalhadoMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        boolean ativo,
        Endereco endereco
) {
    public RetornoDetalhadoMedico(MedicoEntity medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.isAtivo(), medico.getEndereco());
    }
}
