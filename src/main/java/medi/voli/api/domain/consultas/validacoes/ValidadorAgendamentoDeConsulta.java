package medi.voli.api.domain.consultas.validacoes;

import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;

public interface ValidadorAgendamentoDeConsulta {
    void validar (DadosCadastroConsulta dados);
}
