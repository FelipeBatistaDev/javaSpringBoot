package medi.voli.api.domain.consultas;

import medi.voli.api.domain.consultas.dto.DadosAtualizarConsulta;
import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;
import medi.voli.api.domain.consultas.repository.ConsultaEntity;
import medi.voli.api.domain.consultas.repository.ConsultaRepository;
import medi.voli.api.domain.medicos.repository.MedicoEntity;
import medi.voli.api.domain.medicos.repository.MedicoRepository;
import medi.voli.api.domain.pacientes.repository.PacienteRepository;
import medi.voli.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    public ConsultaEntity agendar(DadosCadastroConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id de paciente que não existe");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id de médico que não existe");
        }


        var paciente =  pacienteRepository.findById(dados.idPaciente()).get();

        var medico =  escolherMedico(dados);

        var consulta = new ConsultaEntity(null, medico, paciente, true, dados.data());

       return consultaRepository.save(consulta);

    }

    public ConsultaEntity atualizar(DadosAtualizarConsulta atualizar){
        var consulta = consultaRepository.getReferenceById(atualizar.idConsulta());

        if(atualizar.idMedico() != null){
            var medico = medicoRepository.getReferenceById(atualizar.idMedico());
            consulta.setMedico(medico);
        }

        if(atualizar.data() != null){
            consulta.setData(atualizar.data());
        }

        return consultaRepository.save(consulta);
    }

    private MedicoEntity escolherMedico(DadosCadastroConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade obrigatoria quando não passa médico");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }

    public void cancelarConsulta(Long id) {
        var consulta = consultaRepository.getReferenceById(id);
        consulta.setAtivo(false);
        consultaRepository.save(consulta);
    }
}
