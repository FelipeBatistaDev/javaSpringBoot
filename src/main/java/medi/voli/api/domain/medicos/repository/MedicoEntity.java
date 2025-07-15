package medi.voli.api.domain.medicos.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medi.voli.api.domain.enderecos.repository.Endereco;
import medi.voli.api.domain.medicos.dto.DadosCadastroMedico;
import medi.voli.api.domain.medicos.dto.DadosEditaveis;
import medi.voli.api.domain.medicos.dto.enums.Especialidade;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
@Entity(name = "Medico")
public class MedicoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private boolean ativo;

    @Embedded
    private Endereco endereco;

    public MedicoEntity(DadosCadastroMedico medico) {
        this.nome = medico.nome();
        this.email = medico.email();
        this.crm = medico.crm();
        this.telefone = medico.telefone();
        this.ativo = true;
        this.especialidade=  medico.especialidade();
        this. endereco = new Endereco(medico.endereco());
    }

    public void atualizarInfos(DadosEditaveis input) {
        if(input.nome() != null){
            this.nome = input.nome();
        }

        if(input.email() != null){
            this.email = input.email();
        }

        if(input.telefone() != null){
            this.telefone = input.telefone();
        }

        if (input.endereco() != null){
            this.endereco.atualizarDadosEndereco(input.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
