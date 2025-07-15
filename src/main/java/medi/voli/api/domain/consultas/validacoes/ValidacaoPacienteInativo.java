package medi.voli.api.domain.consultas.validacoes;

import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;
import medi.voli.api.domain.pacientes.repository.PacienteRepository;
import medi.voli.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteInativo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosCadastroConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("Paciente está inativo");
        }
    }
}
