package medi.voli.api.domain.consultas.repository;

import jakarta.persistence.*;
import lombok.*;
import medi.voli.api.domain.consultas.dto.DadosAtualizarConsulta;
import medi.voli.api.domain.medicos.repository.MedicoEntity;
import medi.voli.api.domain.pacientes.repository.PacienteEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "consultas")
@Entity(name = "Consulta")
public class ConsultaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private MedicoEntity medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;

    private Boolean ativo;

    private LocalDateTime data;

    public void atualizar(MedicoEntity medico, DadosAtualizarConsulta atualizacao) {

    }
}
