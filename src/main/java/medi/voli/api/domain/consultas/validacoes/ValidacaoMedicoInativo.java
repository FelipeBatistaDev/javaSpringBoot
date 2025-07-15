package medi.voli.api.domain.consultas.validacoes;

import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;
import medi.voli.api.domain.medicos.repository.MedicoRepository;
import medi.voli.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoInativo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosCadastroConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var medico = medicoRepository.findAtivoById(dados.idMedico());
        if(!medico){
            throw new ValidacaoException("Médico está inativo");
        }
    }

}
