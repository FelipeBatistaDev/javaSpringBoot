package medi.voli.api.domain.consultas.validacoes;

import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;
import medi.voli.api.domain.consultas.repository.ConsultaRepository;
import medi.voli.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoMedicoMesmoDia implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosCadastroConsulta dados){
        var medicoPossuiConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiConsultaMesmoHorario){
            throw new ValidacaoException("Médico tem consulta agendada nesse mesmo horário");
        }
    }
}
