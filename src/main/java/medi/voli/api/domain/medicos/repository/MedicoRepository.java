package medi.voli.api.domain.medicos.repository;

import medi.voli.api.domain.medicos.dto.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    Page<MedicoEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m
             where m.ativo = true
             and
              m.especialidade = :especialidade
             and
             m.id not in(
                Select c.medico.id from Consulta c
                where c.data = :data
             )
             order by rand()
             limit 1
""")
    MedicoEntity escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}