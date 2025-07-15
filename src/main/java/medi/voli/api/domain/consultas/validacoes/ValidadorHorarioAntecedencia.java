package medi.voli.api.domain.consultas.validacoes;

import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;
import medi.voli.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosCadastroConsulta dados){
        var dataConsulta = dados.data();

        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta precisa ser agendada no mínimo 30 minutos antes");
        }
    }
}
