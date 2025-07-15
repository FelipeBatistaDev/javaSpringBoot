package medi.voli.api.domain.pacientes.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medi.voli.api.domain.enderecos.dto.DadosEndereco;
import medi.voli.api.domain.pacientes.dto.DadosPacienteCadastro;
import medi.voli.api.domain.pacientes.dto.DadosParaAtualizar;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
@Entity(name = "Paciente")
public class PacienteEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private Boolean ativo;

    @Embedded
    private DadosEndereco endereco;

    public PacienteEntity(DadosPacienteCadastro paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.telefone = paciente.telefone();
        this.cpf = paciente.cpf();
        this.endereco = paciente.endereco();
        this.ativo = true;
    }

    public void atualizarDados(DadosParaAtualizar input) {
        if(input.nome() != null){
            this.nome = input.nome();
        }

        if(input.email() != null){
            this.email = input.email();
        }
    }

    public void deleteLogico() {
        this.ativo = false;
    }
}
