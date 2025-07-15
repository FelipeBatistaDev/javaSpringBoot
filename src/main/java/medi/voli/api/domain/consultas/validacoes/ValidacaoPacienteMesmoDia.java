package medi.voli.api.domain.consultas.validacoes;

import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;
import medi.voli.api.domain.consultas.repository.ConsultaRepository;
import medi.voli.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteMesmoDia implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosCadastroConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHoraio = dados.data().withHour(18);

        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHoraio);

        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente tem consulta agendada nesse mesmo dia!");
        }
    }
}
