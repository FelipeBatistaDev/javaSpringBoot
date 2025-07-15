package medi.voli.api.domain.consultas.validacoes;

import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;
import medi.voli.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosCadastroConsulta dados){
        var dataConsulta = dados.data();

        var domingo =  dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora do agendamento da consulta");
        }
    }
}
